package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.strategy.CircleShootStrategy;
import edu.hitsz.strategy.DirectShootStrategy;
import edu.hitsz.strategy.SpreadShootStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft hero;

    @BeforeEach
    void setUp() {
        HeroAircraft.resetInstance();
        hero = HeroAircraft.getInstance(100, 600, 0, 0, 100);
    }

    @AfterEach
    void tearDown() {
        HeroAircraft.resetInstance();
    }

    @Test
    void getInstanceShouldReturnSameSingleton() {
        HeroAircraft another = HeroAircraft.getInstance(200, 500, 1, 1, 50);

        assertSame(hero, another);
        assertEquals(100, another.getLocationX());
        assertEquals(100, another.getHp());
    }

    @Test
    void decreaseHpShouldReduceHpAndMarkDeadAtZero() {
        hero.decreaseHp(30);
        assertEquals(70, hero.getHp());
        assertTrue(hero.isAlive());

        hero.decreaseHp(70);
        assertEquals(0, hero.getHp());
        assertFalse(hero.isAlive());
        assertTrue(hero.notValid());
    }

    @Test
    void shootShouldUseCurrentStrategy() {
        assertInstanceOf(DirectShootStrategy.class, hero.getShootStrategy());
        List<BaseBullet> directBullets = hero.shoot();
        assertEquals(1, directBullets.size());

        hero.setShootStrategy(new SpreadShootStrategy());
        List<BaseBullet> spreadBullets = hero.shoot();
        assertEquals(3, spreadBullets.size());

        hero.setShootStrategy(new CircleShootStrategy());
        List<BaseBullet> circleBullets = hero.shoot();
        assertEquals(20, circleBullets.size());
    }

    @Test
    void inheritedLocationSetterShouldUpdatePosition() {
        hero.setLocation(128.8, 512.2);

        assertEquals(128, hero.getLocationX());
        assertEquals(512, hero.getLocationY());
    }
}
