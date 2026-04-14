package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * Boss敌机工厂类
 * 负责创建Boss敌机实例
 */
public class BossEnemyFactory implements EnemyFactory {

    @Override
    public AbstractEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 3;  // Boss仅左右移动
        int speedY = 0;
        int hp = 200;
        return new BossEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
