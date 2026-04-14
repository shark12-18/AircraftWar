package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteProEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 王牌敌机工厂类
 * 负责创建王牌敌机实例
 */
public class EliteProEnemyFactory implements EnemyFactory {

    @Override
    public AbstractEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 3;  // 王牌敌机可左右移动
        int speedY = 5;
        int hp = 100;
        return new EliteProEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
