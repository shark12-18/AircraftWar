package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;

/**
 * 敌机工厂接口
 * 采用工厂方法模式，定义创建敌机的抽象方法，
 * 由各具体工厂子类决定实例化哪种敌机
 */
public interface EnemyFactory {

    /**
     * 创建敌机实例
     * @return 具体的敌机实例
     */
    AbstractEnemy createEnemy();
}
