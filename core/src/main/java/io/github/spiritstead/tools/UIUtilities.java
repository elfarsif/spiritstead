package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class UIUtilities {

    static public float getXforCenteredText(BitmapFont font, GlyphLayout layout, String text) {
        layout.setText(font, text);
        return ScreenSetting.SCREEN_WIDTH / 2 - layout.width / 2;

    }
}
