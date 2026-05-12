package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.observer.PropObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 道具抽象父类
 * 定义所有道具的公共属性和行为
 */
public abstract class AbstractProp extends AbstractFlyingObject {
    
    // 道具效果持续时间（帧数）
    protected int effectDuration;
    private final List<PropObserver> observers = new ArrayList<>();

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
        if (this.locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    /**
     * 激活道具效果（抽象方法）
     * 每个具体道具类需要实现自己的效果逻辑
     */
    public abstract void activate();

    public void addObserver(PropObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PropObserver observer) {
        observers.remove(observer);
    }

    protected void notifyBombObservers() {
        for (PropObserver observer : observers) {
            observer.onBomb();
        }
    }

    protected void notifyFreezeObservers() {
        for (PropObserver observer : observers) {
            observer.onFreeze();
        }
    }

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
