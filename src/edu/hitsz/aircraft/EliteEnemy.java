package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机类
 * 具有中等生命值和射击能力
 */
public class EliteEnemy extends AbstractEnemy {
    
    // 射击频率（帧数）
    private int shootFrequency;
    
    // 子弹威力
    private int power;

    /**
     * 精英敌机构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 20); // 精英敌机得分为20
        this.shootFrequency = 60; // 每60帧射击一次
        this.power = 10; // 子弹威力为10
    }

    @Override
    public void forward() {
        // 精英敌机直线向下移动
        this.locationY += this.speedY;
        // 飞出屏幕下方则消失
        if (this.locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> bullets = new LinkedList<>();
        
        // 向下发射一颗子弹
        int bulletX = this.getLocationX();
        int bulletY = this.getLocationY() + this.getHeight() / 2;
        int bulletSpeedY = this.getSpeedY() + 5; // 子弹速度比敌机快
        
        BaseBullet bullet = new EnemyBullet(bulletX, bulletY, 0, bulletSpeedY, power);
        bullets.add(bullet);
        
        return bullets;
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