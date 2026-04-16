package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 不射击策略
 * 用于普通敌机等不具备射击能力的机型
 */
public class NoShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power) {
        return new LinkedList<>();
    }
}
