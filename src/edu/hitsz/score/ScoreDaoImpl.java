package edu.hitsz.score;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 基于文件的排行榜 DAO 实现。
 */
public class ScoreDaoImpl implements ScoreDao {

    private static final String DEFAULT_DATA_DIR = "data";
    private final Path dataDir;

    public ScoreDaoImpl() {
        this(Paths.get(DEFAULT_DATA_DIR));
    }

    public ScoreDaoImpl(Path dataDir) {
        this.dataDir = dataDir;
    }

    @Override
    public void addRecord(ScoreRecord record) {
        try {
            Files.createDirectories(dataDir);
            try (BufferedWriter writer = Files.newBufferedWriter(
                    getFile(record.getDifficulty()),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                writer.write(record.toCsvLine());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to save score record", e);
        }
    }

    @Override
    public List<ScoreRecord> getRecords(String difficulty) {
        Path file = getFile(difficulty);
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }

        List<ScoreRecord> records = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                records.add(ScoreRecord.fromCsvLine(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load score records", e);
        }
        sortRecords(records);
        return records;
    }

    @Override
    public void deleteRecord(String difficulty, int rank) {
        List<ScoreRecord> records = getRecords(difficulty);
        int index = rank - 1;
        if (index < 0 || index >= records.size()) {
            return;
        }
        records.remove(index);
        rewriteRecords(difficulty, records);
    }

    private void rewriteRecords(String difficulty, List<ScoreRecord> records) {
        try {
            Files.createDirectories(dataDir);
            try (BufferedWriter writer = Files.newBufferedWriter(
                    getFile(difficulty),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING)) {
                for (ScoreRecord record : records) {
                    writer.write(record.toCsvLine());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to rewrite score records", e);
        }
    }

    private Path getFile(String difficulty) {
        return dataDir.resolve(difficulty + ".csv");
    }

    private void sortRecords(List<ScoreRecord> records) {
        records.sort(Comparator
                .comparingInt(ScoreRecord::getScore).reversed()
                .thenComparing(ScoreRecord::getRecordTime));
    }
}
