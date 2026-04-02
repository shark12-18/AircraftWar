package edu.hitsz.prop;

/**
 * 炸弹道具类
 * 提供全屏清敌效果，清除屏幕上所有敌机
 */
public class BombProp extends AbstractProp {
    
    // 爆炸半径（像素）
    private int explosionRadius;
    
    // 爆炸伤害
    private int explosionDamage;

    /**
     * 炸弹道具构造函数
     * @param locationX 初始X坐标
     * @param locationY 初始Y坐标
     * @param speedX X轴速度
     * @param speedY Y轴速度
     */
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 0); // 炸弹道具立即生效，无持续时间
        this.explosionRadius = 1000; // 全屏爆炸半径
        this.explosionDamage = 9999; // 极大伤害值，确保消灭所有敌机
    }

    @Override
    public void activate() {
        // 炸弹道具激活逻辑
        System.out.println("炸弹道具激活：全屏爆炸，清除所有敌机");
        
        // 这里应该调用游戏管理器的全屏清敌方法
        // 道具使用后消失
        this.vanish();
    }

    /**
     * 获取爆炸半径
     * @return 爆炸半径（像素）
     */
    public int getExplosionRadius() {
        return explosionRadius;
    }

    /**
     * 设置爆炸半径
     * @param explosionRadius 新的爆炸半径
     */
    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    /**
     * 获取爆炸伤害
     * @return 爆炸伤害
     */
    public int getExplosionDamage() {
        return explosionDamage;
    }

    /**
     * 设置爆炸伤害
     * @param explosionDamage 新的爆炸伤害
     */
    public void setExplosionDamage(int explosionDamage) {
        this.explosionDamage = explosionDamage;
    }
}