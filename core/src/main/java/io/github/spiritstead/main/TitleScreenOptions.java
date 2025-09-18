package io.github.spiritstead.main;

public enum TitleScreenOptions {
    NEW_GAME(0),
    LOAD_GAME(1),
    QUIT(2);

    private final int value;

    TitleScreenOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
