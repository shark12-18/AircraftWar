package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.*;
import edu.hitsz.prop.*;
import edu.hitsz.strategy.CircleShootStrategy;
import edu.hitsz.strategy.SpreadShootStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    //调度器, 用于定时任务调度
    private final Timer timer;
    //时间间隔(ms)，控制刷新频率
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;

    //敌机工厂实例（工厂方法模式）
    private final EnemyFactory mobEnemyFactory = new MobEnemyFactory();
    private final EnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
    private final EnemyFactory elitePlusEnemyFactory = new ElitePlusEnemyFactory();
    private final EnemyFactory eliteProEnemyFactory = new EliteProEnemyFactory();
    private final EnemyFactory bossEnemyFactory = new BossEnemyFactory();

    //屏幕中出现的敌机最大数量
    private final int enemyMaxNumber = 5;

    //敌机生成周期（每隔固定周期随机产生一种敌机）
    protected double enemySpawnCycle = 20;
    private int enemySpawnCounter = 0;

    //Boss敌机生成：由分数阈值触发
    private int bossScoreThreshold = 500;
    private int lastBossScore = 0;
    //道具生成概率（精英敌机坠毁时）
    private final double propDropProbability = 0.3;

    //英雄机和敌机射击周期
    protected double shootCycle = 20;
    private int shootCounter = 0;

    //当前玩家分数
    private int score = 0;

    //游戏结束标志
    private boolean gameOverFlag = false;

    public Game() {
        heroAircraft = HeroAircraft.getInstance(
                Main.WINDOW_WIDTH / 2,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                0, 0, 100);

        enemyAircrafts = new CopyOnWriteArrayList<>();
        heroBullets = new CopyOnWriteArrayList<>();
        enemyBullets = new CopyOnWriteArrayList<>();
        props = new CopyOnWriteArrayList<>();

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

        this.timer = new Timer("game-action-timer", true);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、及结束判定
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                enemySpawnCounter++;

                // 每隔固定周期，随机产生一种敌机（Boss除外），且只生成一架
                if (enemySpawnCounter >= enemySpawnCycle) {
                    enemySpawnCounter = 0;
                    if (enemyAircrafts.size() < enemyMaxNumber) {
                        double rand = Math.random();
                        EnemyFactory factory;
                        if (rand < 0.4) {
                            factory = mobEnemyFactory;
                        } else if (rand < 0.65) {
                            factory = eliteEnemyFactory;
                        } else if (rand < 0.85) {
                            factory = elitePlusEnemyFactory;
                        } else {
                            factory = eliteProEnemyFactory;
                        }
                        enemyAircrafts.add(factory.createEnemy());
                    }
                }

                // Boss敌机：玩家分数达到阈值时触发生成
                if (score - lastBossScore >= bossScoreThreshold) {
                    lastBossScore += bossScoreThreshold;
                    enemyAircrafts.add(bossEnemyFactory.createEnemy());
                }

                // 飞机发射子弹
                shootAction();
                // 子弹移动
                bulletsMoveAction();
                // 飞机移动
                aircraftsMoveAction();
                // 撞击检测
                crashCheckAction();
                // 后处理
                postProcessAction();
                // 重绘界面
                repaint();
                // 游戏结束检查
                checkResultAction();
            }
        };
        // 以固定延迟时间进行执行：本次任务执行完成后，延迟 timeInterval 再执行下一次
        timer.schedule(task,0,timeInterval);

    }

    //***********************
    //      Action 各部分
    //***********************

    private void shootAction() {
        shootCounter++;
        if (shootCounter >= shootCycle) {
            shootCounter = 0;
            // 英雄机射击
            heroBullets.addAll(heroAircraft.shoot());

            // 所有敌机统一调用shoot()（不射击的敌机通过NoShootStrategy返回空列表）
            for (AbstractAircraft enemy : enemyAircrafts) {
                enemyBullets.addAll(enemy.shoot());
            }
        }
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄机
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (bullet.crash(heroAircraft) || heroAircraft.crash(bullet)) {
                // 英雄机被敌机子弹击中
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                System.out.println("英雄机被击中！当前生命值：" + heroAircraft.getHp());
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // 使用AbstractEnemy的getScore()统一获取分数
                        if (enemyAircraft instanceof AbstractEnemy) {
                            score += ((AbstractEnemy) enemyAircraft).getScore();
                        }

                        // 道具掉落逻辑：不同敌机有不同的掉落概率和道具范围
                        if (enemyAircraft instanceof EliteEnemy) {
                            // 精英敌机：30%概率掉落道具（加血、火力、超级火力）
                            if (Math.random() < propDropProbability) {
                                generateProp(enemyAircraft);
                            }
                        } else if (enemyAircraft instanceof ElitePlusEnemy) {
                            // 精锐敌机：50%概率掉落道具（4种，不含冰冻）
                            if (Math.random() < 0.5) {
                                generateProp(enemyAircraft);
                            }
                        } else if (enemyAircraft instanceof EliteProEnemy) {
                            // 王牌敌机：70%概率掉落道具（全部5种）
                            if (Math.random() < 0.7) {
                                generateProp(enemyAircraft);
                            }
                        } else if (enemyAircraft instanceof BossEnemy) {
                            // Boss敌机：必定掉落3个道具
                            for (int i = 0; i < 3; i++) {
                                generateProp(enemyAircraft);
                            }
                        }
                        // 普通敌机不掉落道具
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    if (enemyAircraft.notValid()) {
                        continue; // 跳过已销毁的敌机
                    }
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(heroAircraft.getHp()); // 设置为当前生命值，确保归零但不溢出
                }
            }
        }

        // 英雄机获得道具，道具生效
        for (AbstractProp prop : props) {
            if (prop.notValid()) {
                continue;
            }
            if (prop.crash(heroAircraft) || heroAircraft.crash(prop)) {
                // 英雄机拾取道具
                prop.activate();
                applyPropEffect(prop);
                prop.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }
    
    /**
     * 生成道具（使用简单工厂模式）
     * 根据敌机类型确定可掉落的道具范围，随机生成一种道具
     * @param enemy 被击毁的敌机
     */
    private void generateProp(AbstractAircraft enemy) {
        // 根据敌机类型确定可掉落的道具种类
        PropFactory.PropType[] availableTypes;

        if (enemy instanceof ElitePlusEnemy) {
            // 精锐敌机：可掉落4种道具（不含冰冻）
            availableTypes = new PropFactory.PropType[]{
                    PropFactory.PropType.BLOOD,
                    PropFactory.PropType.FIRE,
                    PropFactory.PropType.FIRE_PLUS,
                    PropFactory.PropType.BOMB
            };
        } else if (enemy instanceof EliteProEnemy) {
            // 王牌敌机：可掉落全部5种道具
            availableTypes = PropFactory.PropType.values();
        } else {
            // 精英敌机、Boss等：掉落3种基础道具
            availableTypes = new PropFactory.PropType[]{
                    PropFactory.PropType.BLOOD,
                    PropFactory.PropType.FIRE,
                    PropFactory.PropType.FIRE_PLUS
            };
        }

        // 随机选择一种道具，使用简单工厂创建
        int index = (int) (Math.random() * availableTypes.length);
        AbstractProp prop = PropFactory.createProp(
                availableTypes[index], enemy.getLocationX(), enemy.getLocationY()
        );
        props.add(prop);
    }
    
    /**
     * 应用道具效果
     * @param prop 被拾取的道具
     */
    private void applyPropEffect(AbstractProp prop) {
        if (prop instanceof BloodProp) {
            // 加血道具：恢复生命值
            BloodProp bloodProp = (BloodProp) prop;
            int currentHp = heroAircraft.getHp();
            int maxHp = 100; // 英雄机最大生命值
            int healAmount = bloodProp.getHealAmount();

            // 恢复生命值，但不超过最大值
            int newHp = Math.min(currentHp + healAmount, maxHp);
            heroAircraft.decreaseHp(currentHp - newHp); // 使用负数来恢复生命值

            System.out.println("加血道具生效：恢复" + healAmount + "点生命值");

        } else if (prop instanceof FireProp) {
            // 火力道具：切换英雄机弹道为散射
            System.out.println("FireSupply active!");
            heroAircraft.setShootStrategy(new SpreadShootStrategy());

        } else if (prop instanceof FirePlusProp) {
            // 超级火力道具：切换英雄机弹道为环射
            System.out.println("FirePlusSupply active!");
            heroAircraft.setShootStrategy(new CircleShootStrategy());

        } else if (prop instanceof BombProp) {
            // 炸弹道具：清除所有敌机和敌机子弹
            System.out.println("BombSupply active!");

        } else if (prop instanceof FreezeProp) {
            // 冰冻道具：冻结敌机
            System.out.println("FreezeSupply active!");
        }
    }

    /**
     * 检查游戏是否结束，若结束：关闭线程池
     */
    private void checkResultAction(){
        // 游戏结束检查英雄机是否存活
        if (heroAircraft.getHp() <= 0 && !gameOverFlag) {
            gameOverFlag = true;
            System.out.println("Game Over!");
            // 延迟取消定时器，避免立即停止导致异常
            TimerTask stopTask = new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                }
            };
            Timer stopTimer = new Timer("stop-timer", true);
            stopTimer.schedule(stopTask, 100); // 延迟100ms停止
        }
    };

    //***********************
    //      Paint 各部分
    //***********************
    /**
     * 重写 paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.isEmpty()) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE: " + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE: " + this.heroAircraft.getHp(), x, y);
    }

}