package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.factory.EnemyFactory;

/**
 * 困难难度：节奏更快，Boss 每次生成都会增加血量。
 */
public class HardGame extends Game {

    private static final int DIFFICULTY_UP_INTERVAL = 400;
    private static final int BOSS_HP_STEP = 50;

    private int difficultyLevel = 1;
    private double enemySpeedMultiplier = 1.15;
    private int enemyHpBonus = 10;
    private int bossHpBonus = 0;

    public HardGame() {
        super(Difficulty.HARD);
        enemyMaxNumber = 7;
        enemySpawnCycle = 18;
        heroShootCycle = 24;
        enemyShootCycle = 18;
        bossScoreThreshold = 400;
    }

    @Override
    protected EnemyFactory selectEnemyFactory(double rand) {
        if (rand < 0.20) {
            return mobEnemyFactory;
        } else if (rand < 0.45) {
            return eliteEnemyFactory;
        } else if (rand < 0.70) {
            return elitePlusEnemyFactory;
        }
        return eliteProEnemyFactory;
    }

    @Override
    protected void adjustEnemyByDifficulty(AbstractEnemy enemy) {
        enemy.increaseSpeed(enemySpeedMultiplier);
        enemy.increaseHp(enemyHpBonus);
    }

    @Override
    protected AbstractEnemy createBossEnemy() {
        AbstractEnemy boss = super.createBossEnemy();
        bossHpBonus += BOSS_HP_STEP;
        boss.increaseHp(bossHpBonus);
        System.out.printf("困难难度 Boss 生成：本次血量额外提升%d%n", bossHpBonus);
        return boss;
    }

    @Override
    protected void increaseDifficultyAction() {
        if (getActionTick() > 0 && getActionTick() % DIFFICULTY_UP_INTERVAL == 0) {
            difficultyLevel++;
            enemySpawnCycle = Math.max(8, enemySpawnCycle * 0.90);
            enemySpeedMultiplier += 0.12;
            enemyHpBonus += 15;
            heroShootCycle = Math.min(32, heroShootCycle + 1);
            enemyShootCycle = Math.max(10, enemyShootCycle - 1);
            System.out.printf(
                    "困难难度提升：等级%d，敌机生成周期%.1f，速度倍率%.2f，血量加成%d，英雄射击周期%.1f，敌机射击周期%.1f%n",
                    difficultyLevel, enemySpawnCycle, enemySpeedMultiplier, enemyHpBonus,
                    heroShootCycle, enemyShootCycle);
        }
    }
}
