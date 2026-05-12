package edu.hitsz.application;

import edu.hitsz.score.ScoreDao;
import edu.hitsz.score.ScoreDaoImpl;
import edu.hitsz.score.ScoreRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 实验五排行榜界面。
 */
public class ScoreBoardFrame extends JFrame {

    private final Difficulty difficulty;
    private final ScoreDao scoreDao = new ScoreDaoImpl();
    private final JTable scoreTable = new JTable();
    private final DefaultTableModel tableModel;

    public ScoreBoardFrame(Difficulty difficulty, int score) {
        super(difficulty.getDisplayName() + "难度排行榜");
        this.difficulty = difficulty;
        this.tableModel = new DefaultTableModel(new String[]{"名次", "玩家名", "得分", "记录时间"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (score >= 0) {
            askAndSaveRecord(score);
        }
        initUi();
        refreshTable();
    }

    private void askAndSaveRecord(int score) {
        String playerName = JOptionPane.showInputDialog(
                this,
                "本局得分：" + score + "\n请输入玩家姓名：",
                "保存记录",
                JOptionPane.PLAIN_MESSAGE);
        if (playerName == null) {
            return;
        }
        if (playerName.trim().isEmpty()) {
            playerName = "Player";
        }
        scoreDao.addRecord(new ScoreRecord(difficulty.getCode(), playerName.trim(), score, LocalDateTime.now()));
    }

    private void initUi() {
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 8));

        JLabel title = new JLabel(difficulty.getDisplayName() + "难度排行榜", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        scoreTable.setModel(tableModel);
        scoreTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(scoreTable), BorderLayout.CENTER);

        JButton deleteButton = new JButton("删除选中记录");
        deleteButton.addActionListener(e -> deleteSelectedRecord());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void deleteSelectedRecord() {
        int row = scoreTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "请先选择一条记录。");
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "确定删除选中的记录吗？", "删除确认", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int rank = (int) tableModel.getValueAt(row, 0);
            scoreDao.deleteRecord(difficulty.getCode(), rank);
            refreshTable();
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<ScoreRecord> records = scoreDao.getRecords(difficulty.getCode());
        System.out.println("****************");
        System.out.println(difficulty.getDisplayName() + "难度排行榜");
        System.out.println("****************");
        System.out.println("排名\t玩家名\t得分\t记录时间");
        for (int i = 0; i < records.size(); i++) {
            ScoreRecord record = records.get(i);
            int rank = i + 1;
            tableModel.addRow(new Object[]{rank, record.getPlayerName(), record.getScore(), record.getTimeText()});
            System.out.printf("%d\t%s\t%d\t%s%n", rank, record.getPlayerName(), record.getScore(), record.getTimeText());
        }
        System.out.println("****************");
    }
}
