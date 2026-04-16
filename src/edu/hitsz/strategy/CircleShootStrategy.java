package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 环射策略
 * 发射20颗子弹，均匀分布在360度方向
 * 用于Boss敌机和英雄机拾取超级火力道具后
 */
public class CircleShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(int x, int y, int baseSpeedY, int direction, int power) {
        List<BaseBullet> bullets = new LinkedList<>();
        int bulletCount = 20;

        for (int i = 0; i < bulletCount; i++) {
            double angle = 2 * Math.PI * i / bulletCount;
            int speedX = (int) (Math.cos(angle) * 5);
            int speedY = (int) (Math.sin(angle) * 5) + baseSpeedY;

            BaseBullet bullet;
            if (direction < 0) {
                bullet = new HeroBullet(x, y, speedX, speedY, power);
            } else {
                bullet = new EnemyBullet(x, y, speedX, speedY, power);
            }
            bullets.add(bullet);
        }
        return bullets;
    }
}
