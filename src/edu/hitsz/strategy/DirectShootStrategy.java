package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 单排直射策略
 * 发射1颗子弹，沿直线飞行
 * 用于精英敌机和英雄机默认弹道
 */
public class DirectShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power) {
        List<BaseBullet> bullets = new LinkedList<>();
        int speedY = baseSpeedY + direction * 5;
        int bulletY = y + direction * 2;

        BaseBullet bullet;
        if (direction < 0) {
            bullet = new HeroBullet(x, bulletY, 0, speedY, power);
        } else {
            bullet = new EnemyBullet(x, bulletY, 0, speedY, power);
        }
        bullets.add(bullet);
        return bullets;
    }
}
