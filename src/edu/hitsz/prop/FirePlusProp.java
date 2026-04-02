package edu.hitsz.prop;

/**
 * 超级火力道具类
 * 提供更强的子弹威力提升效果
 */
public class FirePlusProp extends AbstractProp {
    
    // 威力提升量
    private int powerBoost;
    
    // 额外效果：增加射击数量
    private int additionalShots;

    /**
     * 超级火力道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     */
    public FirePlusProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 600); // 效果持续10秒（600帧）
        this.powerBoost = 20; // 威力提升20点
        this.additionalShots = 1; // 额外增加1发子弹
    }

    @Override
    public void activate() {
        // 超级火力道具激活逻辑
        System.out.println("超级火力道具激活：子弹威力提升" + powerBoost + "点，增加" + additionalShots + "发子弹，持续" + effectDuration + "帧");
        
        // 这里应该调用英雄机的威力和射击数量提升方法
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

    /**
     * 获取额外射击数量
     * @return 额外射击数量
     */
    public int getAdditionalShots() {
        return additionalShots;
    }

    /**
     * 设置额外射击数量
     * @param additionalShots 新的额外射击数量
     */
    public void setAdditionalShots(int additionalShots) {
        this.additionalShots = additionalShots;
    }
}