package edu.hitsz.observer;

/**
 * 炸弹和冰冻道具的观察者接口。
 */
public interface PropObserver {

    void onBomb();

    void onFreeze();
}
