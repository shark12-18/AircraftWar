package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 双排直射策略
 * 发射2颗子弹，平行直线飞行
 * 用于精锐敌机
 */
public class DoubleDirectShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power) {
        List<BaseBullet> bullets = new LinkedList<>();
        int speedY = baseSpeedY + direction * 5;
        int bulletY = y + direction * 2;

        if (direction < 0) {
            bullets.add(new HeroBullet(x - 10, bulletY, 0, speedY, power));
            bullets.add(new HeroBullet(x + 10, bulletY, 0, speedY, power));
        } else {
            bullets.add(new EnemyBullet(x - 10, bulletY, 0, speedY, power));
            bullets.add(new EnemyBullet(x + 10, bulletY, 0, speedY, power));
        }
        return bullets;
    }
}
