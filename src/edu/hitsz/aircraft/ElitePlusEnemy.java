package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.strategy.DoubleDirectShootStrategy;
import java.util.List;

/**
 * 精锐敌机类
 * 比精英敌机更强的射击能力和生命值
 */
public class ElitePlusEnemy extends AbstractEnemy {
    
    // 射击频率（帧数）
    private int shootFrequency;
    
    // 子弹威力
    private int power;
    
    // 射击模式（单发/双发）
    private boolean doubleShoot;

    /**
     * 精锐敌机构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     */
    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 30);
        this.shootFrequency = 45;
        this.power = 15;
        this.doubleShoot = true;
        this.shootStrategy = new DoubleDirectShootStrategy();
    }

    @Override
    public void forward() {
        // 精锐敌机可能带有横向移动
        this.locationX += this.speedX;
        this.locationY += this.speedY;

        // 横向边界检测和反弹
        if (this.locationX <= 0 || this.locationX >= Main.WINDOW_WIDTH) {
            this.speedX = -this.speedX;
        }
        // 飞出屏幕下方则消失
        if (this.locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.shoot(this.getLocationX(), this.getLocationY(), this.getSpeedY(), 1, power);
    }

    /**
     * 切换射击模式
     * @param doubleShoot 是否使用双发模式
     */
    public void setDoubleShoot(boolean doubleShoot) {
        this.doubleShoot = doubleShoot;
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