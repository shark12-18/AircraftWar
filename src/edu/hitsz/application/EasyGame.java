package edu.hitsz.application;

import edu.hitsz.factory.EnemyFactory;

/**
 * 简单难度：节奏较慢，不生成 Boss，难度不随时间递增。
 */
public class EasyGame extends Game {

    public EasyGame() {
        super(Difficulty.EASY);
        enemyMaxNumber = 4;
        enemySpawnCycle = 28;
        heroShootCycle = 16;
        enemyShootCycle = 28;
    }

    @Override
    protected EnemyFactory selectEnemyFactory(double rand) {
        if (rand < 0.55) {
            return mobEnemyFactory;
        } else if (rand < 0.85) {
            return eliteEnemyFactory;
        }
        return elitePlusEnemyFactory;
    }

    @Override
    protected boolean canSpawnBoss() {
        return false;
    }
}
