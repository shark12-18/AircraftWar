package edu.hitsz.prop;

/**
 * 加血道具类
 * 提供生命值恢复效果
 */
public class BloodProp extends AbstractProp {
    
    // 治疗量
    private int healAmount;

    /**
     * 加血道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     */
    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 0); // 加血道具立即生效，无持续时间
        this.healAmount = 30; // 恢复30点生命值
    }

    @Override
    public void activate() {
        // 加血道具激活逻辑
        // 这里应该调用英雄机的生命值恢复方法
        System.out.println("加血道具激活：恢复" + healAmount + "点生命值");
        
        // 道具使用后消失
        this.vanish();
    }

    /**
     * 获取治疗量
     * @return 治疗量
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * 设置治疗量
     * @param healAmount 新的治疗量
     */
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
}