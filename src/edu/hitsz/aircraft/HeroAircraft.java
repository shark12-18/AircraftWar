package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.strategy.DirectShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控（单例模式实现）
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {
    
    // 单例实例
    private static HeroAircraft instance;

    // 每次射击发射子弹数量
    private int shootNum = 1;

    // 子弹威力
    private int power = 30;

    // 子弹射击方向 (向上发射：-1，向下发射：1)
    private int direction = -1;
    
    // 生存状态标记
    private boolean isAlive = true;

    /**
     * 私有构造函数（单例模式）
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new DirectShootStrategy(); // 默认单排直射
    }
    
    /**
     * 获取单例实例（延迟初始化）
     * @return HeroAircraft单例实例
     */
    public static HeroAircraft getInstance() {
        if (instance == null) {
            // 默认参数：屏幕中央，100点生命值
            instance = new HeroAircraft(200, 500, 0, 0, 100);
        }
        return instance;
    }
    
    /**
     * 带参数的获取单例实例方法
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     * @return HeroAircraft单例实例
     */
    public static HeroAircraft getInstance(int locationX, int locationY, int speedX, int speedY, int hp) {
        if (instance == null) {
            instance = new HeroAircraft(locationX, locationY, speedX, speedY, hp);
        }
        return instance;
    }
    
    /**
     * 重置单例实例（用于游戏重新开始）
     */
    public static void resetInstance() {
        instance = null;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }
    
    @Override
    public void decreaseHp(int decrease) {
        super.decreaseHp(decrease);
        if (this.hp <= 0) {
            this.isAlive = false;
        }
    }
    
    /**
     * 检查英雄机是否存活
     * @return 存活状态
     */
    public boolean isAlive() {
        return isAlive && hp > 0;
    }
    
    /**
     * 复活英雄机
     * @param hp 复活后的生命值
     */
    public void revive(int hp) {
        this.hp = hp;
        this.maxHp = hp;
        this.isAlive = true;
        this.vanish(); // 重置消失状态
    }
    
    /**
     * 复活英雄机（使用最大生命值）
     */
    public void revive() {
        revive(this.maxHp);
    }

    @Override
    public List<BaseBullet> shoot() {
        if (!isAlive()) {
            return new LinkedList<>();
        }
        // 委托给射击策略（direction=-1表示向上发射）
        return shootStrategy.shoot(this.getLocationX(), this.getLocationY(), this.getSpeedY(), -1, power);
    }
    
    /**
     * 设置射击数量（用于道具效果）
     * @param shootNum 新的射击数量
     */
    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }
    
    /**
     * 设置子弹威力（用于道具效果）
     * @param power 新的子弹威力
     */
    public void setPower(int power) {
        this.power = power;
    }
    
    /**
     * 获取当前射击数量
     * @return 射击数量
     */
    public int getShootNum() {
        return shootNum;
    }
    
    /**
     * 获取当前子弹威力
     * @return 子弹威力
     */
    public int getPower() {
        return power;
    }

}