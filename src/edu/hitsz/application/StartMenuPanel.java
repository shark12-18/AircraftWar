package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * 实验五难度选择界面。
 */
public class StartMenuPanel extends JPanel {

    public StartMenuPanel(Consumer<Difficulty> onDifficultySelected) {
        setLayout(new GridBagLayout());
        setBackground(new Color(235, 245, 255));

        JPanel content = new JPanel(new GridLayout(0, 1, 0, 18));
        content.setOpaque(false);

        JLabel title = new JLabel("Aircraft War", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(30, 70, 120));
        content.add(title);

        JLabel subtitle = new JLabel("请选择游戏难度", SwingConstants.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setForeground(new Color(60, 80, 100));
        content.add(subtitle);

        for (Difficulty difficulty : Difficulty.values()) {
            JButton button = new JButton(difficulty.getDisplayName());
            button.setFont(new Font("SansSerif", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(220, 52));
            button.addActionListener(e -> onDifficultySelected.accept(difficulty));
            content.add(button);
        }

        add(content);
    }
}
