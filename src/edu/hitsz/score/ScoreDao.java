package edu.hitsz.score;

import java.util.List;

/**
 * 排行榜数据访问对象接口。
 */
public interface ScoreDao {

    void addRecord(ScoreRecord record);

    List<ScoreRecord> getRecords(String difficulty);

    void deleteRecord(String difficulty, int rank);
}
