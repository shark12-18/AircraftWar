package edu.hitsz.prop;

/**
 * 火力道具类
 * 提供子弹威力提升效果
 */
public class FireProp extends AbstractProp {
    
    // 威力提升量
    private int powerBoost;

    /**
     * 火力道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     */
    public FireProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 300); // 效果持续5秒（300帧）
        this.powerBoost = 10; // 威力提升10点
    }

    @Override
    public void activate() {
        // 火力道具激活逻辑
        System.out.println("火力道具激活：子弹威力提升" + powerBoost + "点，持续" + effectDuration + "帧");
        
        // 这里应该调用英雄机的威力提升方法
        // 道具使用后消失
        this.vanish();
    }

    /**
     * 获取威力提升量
     * @return 威力提升量
     */
    public int getPowerBoost() {
        return powerBoost;
    }

    /**
     * 设置威力提升量
     * @param powerBoost 新的威力提升量
     */
    public void setPowerBoost(int powerBoost) {
        this.powerBoost = powerBoost;
    }
}