package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 散射策略
 * 发射3颗子弹，呈扇形散射
 * 用于王牌敌机和英雄机拾取火力道具后
 */
public class SpreadShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power) {
        List<BaseBullet> bullets = new LinkedList<>();
        int speedY = baseSpeedY + direction * 5;
        int bulletY = y + direction * 2;

        if (direction < 0) {
            bullets.add(new HeroBullet(x - 15, bulletY, -2, speedY, power));
            bullets.add(new HeroBullet(x, bulletY, 0, speedY, power));
            bullets.add(new HeroBullet(x + 15, bulletY, 2, speedY, power));
        } else {
            bullets.add(new EnemyBullet(x - 15, bulletY, -2, speedY, power));
            bullets.add(new EnemyBullet(x, bulletY, 0, speedY, power));
            bullets.add(new EnemyBullet(x + 15, bulletY, 2, speedY, power));
        }
        return bullets;
    }
}
