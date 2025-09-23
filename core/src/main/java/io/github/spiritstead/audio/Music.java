package io.github.spiritstead.audio;

public enum Music {
    THEME_SONG("sounds/theme1.wav");

    private final String fileName;

    Music(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
