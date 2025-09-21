package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.ScreenSetting;

public class ToolTip {
    private SpriteBatch batch;
    private Font font;

    public ToolTip(SpriteBatch batch) {
        this.batch = batch;
        this.font = new Font("fonts/maruMonica.fnt");
        this.font.getBitmapFont().getData().setScale(0.5f);
    }

    public void draw() {
        font.getBitmapFont().draw(batch, "[ press space ]", ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
    }
}
