package io.github.spiritstead.ui;

import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.screens.OptionCursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Options {
    ArrayList<String> list = new ArrayList<>();
    Font font;
    int yDiff;
    public float x;
    public float y;

    public Options(Font font, int yDiff) {
        this.font = font;
        this.yDiff = yDiff;
    }

    public Options(Font font, int yDiff, ArrayList<String> list) {
        this(font, yDiff);
        this.list = list;
    }

    public void draw(float x, float y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < list.size(); i++) {
            float tempY = y - yDiff * i;
            font.getBitmapFont().draw(Game.batch, list.get(i), x, tempY);
        }
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

}
