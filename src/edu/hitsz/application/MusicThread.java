package edu.hitsz.application;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * 音频播放线程，支持循环播放和停止。
 */
public class MusicThread extends Thread {

    private final String audioPath;
    private final boolean loop;
    private volatile boolean running = true;
    private Clip clip;

    public MusicThread(String audioPath, boolean loop) {
        this.audioPath = audioPath;
        this.loop = loop;
        setDaemon(true);
    }

    @Override
    public void run() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioPath))) {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
            while (running && clip.isOpen() && (loop || clip.isRunning())) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.err.println("音频播放失败: " + audioPath + " (" + e.getMessage() + ")");
        } finally {
            closeClip();
        }
    }

    public void stopMusic() {
        running = false;
        closeClip();
    }

    private void closeClip() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }
    }
}
