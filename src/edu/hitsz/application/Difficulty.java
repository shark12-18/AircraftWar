package edu.hitsz.application;

/**
 * 游戏难度选项。实验五只使用难度来切换地图与排行榜文件。
 */
public enum Difficulty {
    EASY("easy", "简单", "src/images/bg.jpg"),
    NORMAL("normal", "普通", "src/images/bg2.jpg"),
    HARD("hard", "困难", "src/images/bg3.jpg");

    private final String code;
    private final String displayName;
    private final String backgroundPath;

    Difficulty(String code, String displayName, String backgroundPath) {
        this.code = code;
        this.displayName = displayName;
        this.backgroundPath = backgroundPath;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }
}
