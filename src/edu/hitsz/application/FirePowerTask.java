package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.DirectShootStrategy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 火力道具恢复任务，使用 Runnable 实现多线程。
 */
public class FirePowerTask implements Runnable {

    private final HeroAircraft heroAircraft;
    private final AtomicInteger versionHolder;
    private final int version;
    private final long durationMillis;

    public FirePowerTask(HeroAircraft heroAircraft, AtomicInteger versionHolder, int version, long durationMillis) {
        this.heroAircraft = heroAircraft;
        this.versionHolder = versionHolder;
        this.version = version;
        this.durationMillis = durationMillis;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(durationMillis);
            if (versionHolder.get() == version && heroAircraft.isAlive()) {
                heroAircraft.setShootStrategy(new DirectShootStrategy());
                System.out.println("FireSupply expired, hero shoot strategy reset.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
