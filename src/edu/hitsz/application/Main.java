package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    private static final CardLayout CARD_LAYOUT = new CardLayout();
    private static final JPanel CARD_PANEL = new JPanel(CARD_LAYOUT);

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartMenuPanel startMenuPanel = new StartMenuPanel(difficulty -> startGame(frame, difficulty));
        CARD_PANEL.add(startMenuPanel, "start");
        frame.add(CARD_PANEL);
        frame.setVisible(true);
    }

    private static void startGame(JFrame frame, Difficulty difficulty) {
        HeroAircraft.resetInstance();
        Game game = createGame(difficulty);
        CARD_PANEL.add(game, "game");
        CARD_LAYOUT.show(CARD_PANEL, "game");
        frame.revalidate();
        game.action();
        game.requestFocusInWindow();
    }

    private static Game createGame(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new EasyGame();
            case HARD:
                return new HardGame();
            case NORMAL:
            default:
                return new NormalGame();
        }
    }
}
