package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 王牌敌机类
 * 具有特殊能力和更高的生命值
 */
public class EliteProEnemy extends AbstractEnemy {
    
    // 射击频率（帧数）
    private int shootFrequency;
    
    // 子弹威力
    private int power;
    
    // 特殊能力激活状态
    private boolean specialAbilityActive;
    
    // 特殊能力冷却时间
    private int abilityCooldown;

    /**
     * 王牌敌机构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     */
    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 50); // 王牌敌机得分为50
        this.shootFrequency = 30; // 每30帧射击一次
        this.power = 20; // 子弹威力为20
        this.specialAbilityActive = false;
        this.abilityCooldown = 0;
    }

    @Override
    public void forward() {
        // 王牌敌机具有复杂的移动模式
        if (specialAbilityActive) {
            // 特殊能力激活时移动更快
            this.locationX += this.speedX * 2;
            this.locationY += this.speedY * 2;
        } else {
            this.locationX += this.speedX;
            this.locationY += this.speedY;
        }
        
        // 边界检测和反弹
        if (this.locationX <= 0 || this.locationX >= 512) {
            this.speedX = -this.speedX;
        }
        
        // 更新特殊能力冷却
        if (abilityCooldown > 0) {
            abilityCooldown--;
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> bullets = new LinkedList<>();
        
        int bulletX = this.getLocationX();
        int bulletY = this.getLocationY() + this.getHeight() / 2;
        int bulletSpeedY = this.getSpeedY() + 5;
        
        if (specialAbilityActive) {
            // 特殊能力激活时：三发散射
            BaseBullet bullet1 = new EnemyBullet(bulletX - 15, bulletY, -2, bulletSpeedY, power);
            BaseBullet bullet2 = new EnemyBullet(bulletX, bulletY, 0, bulletSpeedY, power);
            BaseBullet bullet3 = new EnemyBullet(bulletX + 15, bulletY, 2, bulletSpeedY, power);
            bullets.add(bullet1);
            bullets.add(bullet2);
            bullets.add(bullet3);
        } else {
            // 普通射击：双发
            BaseBullet bullet1 = new EnemyBullet(bulletX - 10, bulletY, 0, bulletSpeedY, power);
            BaseBullet bullet2 = new EnemyBullet(bulletX + 10, bulletY, 0, bulletSpeedY, power);
            bullets.add(bullet1);
            bullets.add(bullet2);
        }
        
        return bullets;
    }

    /**
     * 激活特殊能力
     */
    public void activateSpecialAbility() {
        if (abilityCooldown <= 0) {
            this.specialAbilityActive = true;
            this.abilityCooldown = 300; // 5秒冷却（假设60帧/秒）
        }
    }

    /**
     * 关闭特殊能力
     */
    public void deactivateSpecialAbility() {
        this.specialAbilityActive = false;
    }

    /**
     * 检查特殊能力是否激活
     * @return 特殊能力激活状态
     */
    public boolean isSpecialAbilityActive() {
        return specialAbilityActive;
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