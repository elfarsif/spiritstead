package io.github.spiritstead.screens;

import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;

public class OptionCursor {
    private Font font;
    public int optionNum = 0;
    private String cursor = ">";

    public OptionCursor(Font font) {
        this.font = font;
    }

    public void draw(float x, float y) {
        font.getBitmapFont().draw(
            Game.batch,
            ">",
            x,
            y
        );
    }
}
