package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 普通敌机工厂类
 * 负责创建普通敌机实例
 */
public class MobEnemyFactory implements EnemyFactory {

    @Override
    public AbstractEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 10;
        int hp = 30;
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
