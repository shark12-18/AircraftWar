package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import java.util.List;

/**
 * 射击策略接口（策略模式）
 * 定义弹道发射算法的统一接口，不同策略实现不同的弹道逻辑
 */
public interface ShootStrategy {

    /**
     * 执行射击，生成子弹列表
     * @param x 飞机当前X坐标
     * @param y 飞机当前Y坐标
     * @param baseSpeedY 飞机自身的Y轴速度（子弹在此基础上叠加）
     * @param direction 射击方向，-1表示向上（英雄机），1表示向下（敌机）
     * @param power 子弹威力
     * @return 生成的子弹列表
     */
    List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power);
}
