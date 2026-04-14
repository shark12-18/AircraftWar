package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 精英敌机工厂类
 * 负责创建精英敌机实例
 */
public class EliteEnemyFactory implements EnemyFactory {

    @Override
    public AbstractEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 8;
        int hp = 60;
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
