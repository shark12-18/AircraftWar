package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 精锐敌机工厂类
 * 负责创建精锐敌机实例
 */
public class ElitePlusEnemyFactory implements EnemyFactory {

    @Override
    public AbstractEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 2;  // 精锐敌机可左右移动
        int speedY = 6;
        int hp = 80;
        return new ElitePlusEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
