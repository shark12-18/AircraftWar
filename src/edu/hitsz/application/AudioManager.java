package edu.hitsz.application;

/**
 * 游戏音效管理类。
 */
public class AudioManager {

    private static final String BGM = "src/videos/bgm.wav";
    private static final String BOSS_BGM = "src/videos/bgm_boss.wav";
    private static final String BULLET_HIT = "src/videos/bullet_hit.wav";
    private static final String GET_SUPPLY = "src/videos/get_supply.wav";
    private static final String BOMB_EXPLOSION = "src/videos/bomb_explosion.wav";
    private static final String GAME_OVER = "src/videos/game_over.wav";

    private MusicThread bgmThread;
    private MusicThread bossBgmThread;

    public void startBackgroundMusic() {
        if (bgmThread == null || !bgmThread.isAlive()) {
            bgmThread = new MusicThread(BGM, true);
            bgmThread.start();
        }
    }

    public void stopBackgroundMusic() {
        if (bgmThread != null) {
            bgmThread.stopMusic();
            bgmThread = null;
        }
    }

    public void startBossMusic() {
        if (bossBgmThread == null || !bossBgmThread.isAlive()) {
            bossBgmThread = new MusicThread(BOSS_BGM, true);
            bossBgmThread.start();
        }
    }

    public void stopBossMusic() {
        if (bossBgmThread != null) {
            bossBgmThread.stopMusic();
            bossBgmThread = null;
        }
    }

    public void playBulletHit() {
        playOnce(BULLET_HIT);
    }

    public void playGetSupply() {
        playOnce(GET_SUPPLY);
    }

    public void playBombExplosion() {
        playOnce(BOMB_EXPLOSION);
    }

    public void playGameOver() {
        playOnce(GAME_OVER);
    }

    public void stopAll() {
        stopBackgroundMusic();
        stopBossMusic();
    }

    private void playOnce(String path) {
        new MusicThread(path, false).start();
    }
}
