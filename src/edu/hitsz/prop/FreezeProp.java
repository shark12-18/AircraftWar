package edu.hitsz.prop;

/**
 * 冰冻道具类
 * 提供敌机冰冻效果，减缓敌机移动和射击速度
 */
public class FreezeProp extends AbstractProp {
    
    // 冰冻持续时间
    private int freezeDuration;
    
    // 移动速度减缓比例（百分比）
    private int speedReduction;
    
    // 射击频率减缓比例（百分比）
    private int shootFrequencyReduction;

    /**
     * 冰冻道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     */
    public FreezeProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 450); // 效果持续7.5秒（450帧）
        this.freezeDuration = 300; // 冰冻效果持续5秒
        this.speedReduction = 50; // 移动速度减缓50%
        this.shootFrequencyReduction = 70; // 射击频率减缓70%
    }

    @Override
    public void activate() {
        // 冰冻道具激活逻辑
        System.out.println("冰冻道具激活：敌机移动速度减缓" + speedReduction + "%，射击频率减缓" + shootFrequencyReduction + "%，持续" + freezeDuration + "帧");
        
        // 这里应该调用游戏管理器的冰冻效果应用方法
        // 道具使用后消失
        this.vanish();
    }

    /**
     * 获取冰冻持续时间
     * @return 冰冻持续时间（帧数）
     */
    public int getFreezeDuration() {
        return freezeDuration;
    }

    /**
     * 设置冰冻持续时间
     * @param freezeDuration 新的冰冻持续时间
     */
    public void setFreezeDuration(int freezeDuration) {
        this.freezeDuration = freezeDuration;
    }

    /**
     * 获取移动速度减缓比例
     * @return 移动速度减缓比例（百分比）
     */
    public int getSpeedReduction() {
        return speedReduction;
    }

    /**
     * 设置移动速度减缓比例
     * @param speedReduction 新的移动速度减缓比例
     */
    public void setSpeedReduction(int speedReduction) {
        this.speedReduction = speedReduction;
    }

    /**
     * 获取射击频率减缓比例
     * @return 射击频率减缓比例（百分比）
     */
    public int getShootFrequencyReduction() {
        return shootFrequencyReduction;
    }

    /**
     * 设置射击频率减缓比例
     * @param shootFrequencyReduction 新的射击频率减缓比例
     */
    public void setShootFrequencyReduction(int shootFrequencyReduction) {
        this.shootFrequencyReduction = shootFrequencyReduction;
    }
}