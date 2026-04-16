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
        int bulletCount = 20; // 增加到20颗子弹，形成更完美的圆形
        double baseSpeed = 6.0; // 降低基础速度，让圆形更明显

        for (int i = 0; i < bulletCount; i++) {
            // 计算360度均匀分布的角度
            double angle = 2 * Math.PI * i / bulletCount;
            
            // 计算标准化的速度分量（保持完美的圆形）
            double speedX = Math.cos(angle) * baseSpeed;
            double speedY = Math.sin(angle) * baseSpeed;
            
            // 添加基础速度，但不破坏圆形（使用向量加法）
            if (direction < 0) {
                // 英雄机向上射击：圆形整体向上移动
                speedY -= 3; // 较小的基础速度，保持圆形
            } else {
                // 敌机向下射击：圆形整体向下移动
                speedY += 3; // 较小的基础速度，保持圆形
            }

            BaseBullet bullet;
            if (direction < 0) {
                bullet = new HeroBullet(x, y, (int) Math.round(speedX), (int) Math.round(speedY), power);
            } else {
                bullet = new EnemyBullet(x, y, (int) Math.round(speedX), (int) Math.round(speedY), power);
            }
            bullets.add(bullet);
        }
        return bullets;
    }
}