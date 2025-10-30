package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.ScreenSetting;

public final class ToolTip {
    private static final float SCALE = 0.5f;
    private final Font font;
    private final String text;

    public ToolTip(Font font, String text) {
        this.font = font;
        this.text = text;
        this.font.getBitmapFont().getData().setScale(SCALE);
    }

    public void draw(SpriteBatch batch) {
        font.getBitmapFont().draw(batch, this.text, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
    }
}
