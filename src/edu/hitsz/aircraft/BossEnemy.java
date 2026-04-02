package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * Boss敌机类
 * 具有最高生命值、多阶段能力和特殊攻击模式
 */
public class BossEnemy extends AbstractEnemy {
    
    // 射击频率（帧数）
    private int shootFrequency;
    
    // 子弹威力
    private int power;
    
    // 战斗阶段（1-3）
    private int phase;
    
    // 特殊攻击冷却
    private int specialAttackCooldown;

    /**
     * Boss敌机构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     */
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 100); // Boss敌机得分为100
        this.shootFrequency = 20; // 每20帧射击一次
        this.power = 30; // 子弹威力为30
        this.phase = 1; // 初始阶段为1
        this.specialAttackCooldown = 0;
    }

    @Override
    public void forward() {
        // Boss敌机在屏幕上方水平移动
        this.locationX += this.speedX;
        
        // 边界检测和反弹
        if (this.locationX <= 0 || this.locationX >= 512) {
            this.speedX = -this.speedX;
        }
        
        // 根据阶段调整移动模式
        if (phase == 2) {
            // 阶段2：小幅上下移动
            this.locationY += (int)(Math.sin(System.currentTimeMillis() / 500.0) * 2);
        } else if (phase == 3) {
            // 阶段3：更复杂的移动模式
            this.locationY += (int)(Math.sin(System.currentTimeMillis() / 300.0) * 3);
        }
        
        // 更新特殊攻击冷却
        if (specialAttackCooldown > 0) {
            specialAttackCooldown--;
        }
        
        // 检查阶段转换
        checkPhaseTransition();
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> bullets = new LinkedList<>();
        
        int bulletX = this.getLocationX();
        int bulletY = this.getLocationY() + this.getHeight() / 2;
        int bulletSpeedY = this.getSpeedY() + 5;
        
        switch (phase) {
            case 1:
                // 阶段1：三发直线射击
                bullets.add(new EnemyBullet(bulletX - 20, bulletY, 0, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX, bulletY, 0, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 20, bulletY, 0, bulletSpeedY, power));
                break;
                
            case 2:
                // 阶段2：五发散射
                bullets.add(new EnemyBullet(bulletX - 30, bulletY, -3, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX - 15, bulletY, -1, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX, bulletY, 0, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 15, bulletY, 1, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 30, bulletY, 3, bulletSpeedY, power));
                break;
                
            case 3:
                // 阶段3：七发全方位射击
                bullets.add(new EnemyBullet(bulletX - 30, bulletY, -4, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX - 20, bulletY, -2, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX - 10, bulletY, -1, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX, bulletY, 0, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 10, bulletY, 1, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 20, bulletY, 2, bulletSpeedY, power));
                bullets.add(new EnemyBullet(bulletX + 30, bulletY, 4, bulletSpeedY, power));
                break;
        }
        
        return bullets;
    }

    /**
     * 检查并执行阶段转换
     */
    private void checkPhaseTransition() {
        if (phase == 1 && this.hp <= this.maxHp * 0.66) {
            changePhase(2);
        } else if (phase == 2 && this.hp <= this.maxHp * 0.33) {
            changePhase(3);
        }
    }

    /**
     * 改变战斗阶段
     * @param newPhase 新阶段（1-3）
     */
    public void changePhase(int newPhase) {
        if (newPhase >= 1 && newPhase <= 3 && newPhase != phase) {
            this.phase = newPhase;
            
            // 阶段转换时的能力提升
            if (phase == 2) {
                this.shootFrequency = 15; // 更快射击
                this.power = 40; // 更强威力
            } else if (phase == 3) {
                this.shootFrequency = 10; // 最快射击
                this.power = 50; // 最强威力
            }
        }
    }

    /**
     * 执行特殊攻击
     * @return 特殊攻击产生的子弹列表
     */
    public List<BaseBullet> specialAttack() {
        if (specialAttackCooldown <= 0) {
            specialAttackCooldown = 180; // 3秒冷却
            
            List<BaseBullet> specialBullets = new LinkedList<>();
            
            // 根据阶段执行不同的特殊攻击
            if (phase >= 2) {
                // 环形弹幕攻击
                int centerX = this.getLocationX();
                int centerY = this.getLocationY() + this.getHeight() / 2;
                
                for (int i = 0; i < 8; i++) {
                    double angle = i * Math.PI / 4;
                    int speedX = (int)(Math.cos(angle) * 5);
                    int speedY = (int)(Math.sin(angle) * 5 + 3);
                    specialBullets.add(new EnemyBullet(centerX, centerY, speedX, speedY, power * 2));
                }
            }
            
            return specialBullets;
        }
        
        return new LinkedList<>();
    }

    /**
     * 获取当前战斗阶段
     * @return 战斗阶段（1-3）
     */
    public int getPhase() {
        return phase;
    }

    /**
     * 获取射击频率
     * @return 射击频率（帧数）
     */
    public int getShootFrequency() {
        return shootFrequency;
    }

    /**
     * 获取子弹威力
     * @return 子弹威力
     */
    public int getPower() {
        return power;
    }
}