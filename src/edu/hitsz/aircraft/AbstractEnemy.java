package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import java.util.List;

/**
 * 敌机抽象父类
 * 定义所有敌机的公共属性和行为
 */
public abstract class AbstractEnemy extends AbstractAircraft {
    
    // 敌机得分
    protected int score;

    /**
     * 敌机构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param hp 生命值
     * @param score 击毁得分
     */
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score) {
        super(locationX, locationY, speedX, speedY, hp);
        this.score = score;
    }

    /**
     * 获取敌机得分
     * @return 击毁该敌机可获得的分数
     */
    public int getScore() {
        return score;
    }

    /**
     * 敌机射击方法（抽象方法）
     * @return 射击产生的子弹列表
     */
    @Override
    public abstract List<BaseBullet> shoot();

    /**
     * 敌机移动方法（抽象方法）
     */
    @Override
    public abstract void forward();
}