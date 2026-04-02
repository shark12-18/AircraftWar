package edu.hitsz.prop;

import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 道具抽象父类
 * 定义所有道具的公共属性和行为
 */
public abstract class AbstractProp extends AbstractFlyingObject {
    
    // 道具效果持续时间（帧数）
    protected int effectDuration;

    /**
     * 道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     * @param effectDuration 效果持续时间
     */
    public AbstractProp(int locationX, int locationY, int speedX, int speedY, int effectDuration) {
        super(locationX, locationY, speedX, speedY);
        this.effectDuration = effectDuration;
    }

    /**
     * 道具移动方法
     */
    @Override
    public void forward() {
        // 道具向下移动
        this.locationY += this.speedY;
    }

    /**
     * 激活道具效果（抽象方法）
     * 每个具体道具类需要实现自己的效果逻辑
     */
    public abstract void activate();

    /**
     * 获取道具效果持续时间
     * @return 效果持续时间（帧数）
     */
    public int getEffectDuration() {
        return effectDuration;
    }

    /**
     * 设置道具效果持续时间
     * @param effectDuration 新的效果持续时间
     */
    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }
}