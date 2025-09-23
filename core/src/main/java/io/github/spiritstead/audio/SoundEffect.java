package io.github.spiritstead.audio;

public enum SoundEffect {
    COIN("sounds/coin.wav"),
    POWERUP("sounds/powerup.wav"),
    DIALOGUE("sounds/dialogue.wav");

    private final String fileName;

    SoundEffect(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
