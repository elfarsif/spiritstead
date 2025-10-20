package io.github.spiritstead.screens.titleScreen;

public enum TitleScreenOptions {
    NEW_GAME(0, "NEW GAME"),
    LOAD_GAME(1, "LOAD GAME"),
    QUIT(2, "QUIT");

    private final int value;
    private final String string;

    TitleScreenOptions(int value, String string) {
        this.value = value;
        this.string = string;
    }

    public int getValue() { return this.value; }
    public String getString() { return this.string; }

}
