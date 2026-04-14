package edu.hitsz.factory;

import edu.hitsz.prop.*;

/**
 * 道具简单工厂类
 * 采用简单工厂模式，根据传入的道具类型创建对应的道具实例
 */
public class PropFactory {

    /**
     * 道具类型枚举
     */
    public enum PropType {
        BLOOD,
        FIRE,
        FIRE_PLUS,
        BOMB,
        FREEZE
    }

    /**
     * 根据道具类型创建对应的道具实例
     * @param type 道具类型
     * @param locationX 道具初始X坐标
     * @param locationY 道具初始Y坐标
     * @return 对应类型的道具实例
     */
    public static AbstractProp createProp(PropType type, int locationX, int locationY) {
        switch (type) {
            case BLOOD:
                return new BloodProp(locationX, locationY, 0, 2);
            case FIRE:
                return new FireProp(locationX, locationY, 0, 2);
            case FIRE_PLUS:
                return new FirePlusProp(locationX, locationY, 0, 2);
            case BOMB:
                return new BombProp(locationX, locationY, 0, 2);
            case FREEZE:
                return new FreezeProp(locationX, locationY, 0, 2);
            default:
                throw new IllegalArgumentException("Unknown prop type: " + type);
        }
    }
}
