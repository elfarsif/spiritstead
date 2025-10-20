package io.github.spiritstead.screens.titleScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.tools.BlackTexture;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.OptionCursor;
import io.github.spiritstead.screens.Screen;
import io.github.spiritstead.tools.Options;

import java.util.ArrayList;
import java.util.Arrays;

public class TitleScreen implements Screen {
    private final Font font;
    private final Font titleFont;
    private final GlyphLayout layout = new GlyphLayout();
    private final BlackTexture blackTexture;
    public OptionCursor optionCursor;
    private final Options options;

    private static final String TITLE = "SpiritVale";

    public TitleScreen(Font font, Font titleFont, BlackTexture blackTexture, OptionCursor optionCursor,
                       Options options) {
        this.font = font;
        this.titleFont = titleFont;
        this.blackTexture = blackTexture;
        this.optionCursor = optionCursor;
        this.options = options;
    }

    private void drawTitleScreen() {
        Game.batch.draw(blackTexture.texture, 0, 0);

        //TITLE NAME placment
        this.titleFont.getBitmapFont().getData().setScale(1.5f);
        String text = TITLE;
        float x = getXforCenteredText(titleFont.getBitmapFont(), text);
        float y = (float) (ScreenSetting.SCREEN_HEIGHT * 2) / 3 + layout.height / 2;

        //Shadow
        this.titleFont.getBitmapFont().setColor(Color.GRAY);
        this.titleFont.getBitmapFont().draw(Game.batch, text, x + 2, y - 2);

        //Title
        this.titleFont.getBitmapFont().setColor(Color.WHITE);
        this.titleFont.getBitmapFont().draw(Game.batch, text, x, y);

        y = (float) ScreenSetting.SCREEN_HEIGHT / 2;
        //MENU
        font.getBitmapFont().getData().setScale(1f);

        x = getXforCenteredText(font.getBitmapFont(), text);
        options.draw(x, y - ScreenSetting.TILE_SIZE);

        y -= ScreenSetting.TILE_SIZE;
        if (optionCursor.optionNum == TitleScreenOptions.NEW_GAME.getValue()) {
            optionCursor.draw(options.x - ScreenSetting.TILE_SIZE, y);
        }

        y -= ScreenSetting.TILE_SIZE;
        if (optionCursor.optionNum == TitleScreenOptions.LOAD_GAME.getValue()) {
            optionCursor.draw(options.x - ScreenSetting.TILE_SIZE, y);
        }

        y -= ScreenSetting.TILE_SIZE;
        if (optionCursor.optionNum == TitleScreenOptions.QUIT.getValue()) {
            optionCursor.draw(options.x - ScreenSetting.TILE_SIZE, y);
        }

    }

    private float getXforCenteredText(BitmapFont font, String text) {
        layout.setText(font, text);
        return (float) ScreenSetting.SCREEN_WIDTH / 2 - layout.width / 2;

    }

    public void draw() {
        drawTitleScreen();
    }

    public void dispose() {
        font.getBitmapFont().dispose();
        titleFont.getBitmapFont().dispose();
    }

}
