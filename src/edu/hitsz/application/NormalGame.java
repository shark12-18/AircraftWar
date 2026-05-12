package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.factory.EnemyFactory;

/**
 * 普通难度：Boss 血量固定，敌机生成周期、速度和血量随时间提升。
 */
public class NormalGame extends Game {

    private static final int DIFFICULTY_UP_INTERVAL = 500;

    private int difficultyLevel = 1;
    private double enemySpeedMultiplier = 1.0;
    private int enemyHpBonus = 0;

    public NormalGame() {
        super(Difficulty.NORMAL);
        enemyMaxNumber = 5;
        enemySpawnCycle = 22;
        heroShootCycle = 20;
        enemyShootCycle = 22;
        bossScoreThreshold = 500;
    }

    @Override
    protected EnemyFactory selectEnemyFactory(double rand) {
        if (rand < 0.35) {
            return mobEnemyFactory;
        } else if (rand < 0.65) {
            return eliteEnemyFactory;
        } else if (rand < 0.85) {
            return elitePlusEnemyFactory;
        }
        return eliteProEnemyFactory;
    }

    @Override
    protected void adjustEnemyByDifficulty(AbstractEnemy enemy) {
        enemy.increaseSpeed(enemySpeedMultiplier);
        if (enemyHpBonus > 0) {
            enemy.increaseHp(enemyHpBonus);
        }
    }

    @Override
    protected void increaseDifficultyAction() {
        if (getActionTick() > 0 && getActionTick() % DIFFICULTY_UP_INTERVAL == 0) {
            difficultyLevel++;
            enemySpawnCycle = Math.max(10, enemySpawnCycle * 0.92);
            enemySpeedMultiplier += 0.08;
            enemyHpBonus += 10;
            System.out.printf(
                    "普通难度提升：等级%d，敌机生成周期%.1f，速度倍率%.2f，血量加成%d%n",
                    difficultyLevel, enemySpawnCycle, enemySpeedMultiplier, enemyHpBonus);
        }
    }
}
