package io.github.spiritstead.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.main.GamePanel;

public class UIUtilities {

    static public float getXforCenteredText(BitmapFont font, GlyphLayout layout, String text, GamePanel gp) {
        layout.setText(font, text);
        return gp.sSetting.SCREEN_WIDTH / 2 - layout.width / 2;

    }
}
