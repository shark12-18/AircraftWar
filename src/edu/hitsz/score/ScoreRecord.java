package edu.hitsz.score;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 排行榜分数记录值对象。
 */
public class ScoreRecord {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String difficulty;
    private final String playerName;
    private final int score;
    private final LocalDateTime recordTime;

    public ScoreRecord(String difficulty, String playerName, int score, LocalDateTime recordTime) {
        this.difficulty = difficulty;
        this.playerName = playerName;
        this.score = score;
        this.recordTime = recordTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public String getTimeText() {
        return FORMATTER.format(recordTime);
    }

    public String toCsvLine() {
        return String.join(",",
                escape(difficulty),
                escape(playerName),
                String.valueOf(score),
                getTimeText());
    }

    public static ScoreRecord fromCsvLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid score record: " + line);
        }
        return new ScoreRecord(
                unescape(parts[0]),
                unescape(parts[1]),
                Integer.parseInt(parts[2]),
                LocalDateTime.parse(parts[3], FORMATTER));
    }

    private static String escape(String value) {
        return value == null ? "" : value.replace(",", " ");
    }

    private static String unescape(String value) {
        return value == null ? "" : value;
    }
}
