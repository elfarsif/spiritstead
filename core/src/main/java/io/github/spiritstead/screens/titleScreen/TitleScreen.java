package io.github.spiritstead.screens.titleScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.BlackTexture;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.screens.OptionCursor;
import io.github.spiritstead.screens.Screen;
import io.github.spiritstead.ui.Options;

import java.util.ArrayList;
import java.util.Arrays;

public class TitleScreen implements Screen {
    private GamePanel gp;
    private Font font;
    private Font titleFont;
    private GlyphLayout layout = new GlyphLayout();
    private SpriteBatch batch;
    private BlackTexture blackTexture;
    public OptionCursor optionCursor;
    private Options options;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
        font = new Font("fonts/maruMonicaBold.fnt");
        titleFont = new Font("fonts/alagard_60.fnt");
        this.blackTexture = new BlackTexture(gp.sSetting.SCREEN_WIDTH, gp.sSetting.SCREEN_HEIGHT);
        this.batch = Game.batch;
        this.optionCursor = new OptionCursor(this.font);
        this.options = new Options(font, ScreenSetting.TILE_SIZE, new ArrayList<String>(Arrays.asList(
            TitleScreenOptions.NEW_GAME.getString(),
            TitleScreenOptions.LOAD_GAME.getString(),
            TitleScreenOptions.QUIT.getString()
        )));
    }

    private void drawTitleScreen() {
        batch.draw(blackTexture.texture, 0, 0);

        //TITLE NAME placment
        titleFont.getBitmapFont().getData().setScale(1.5f);
        String text = "Spiritvale";
        float x = getXforCenteredText(titleFont.getBitmapFont(), text);
        float y = gp.sSetting.SCREEN_HEIGHT * 3 / 4 + layout.height / 2;

        //Shadow
        titleFont.getBitmapFont().setColor(Color.GRAY);
        titleFont.getBitmapFont().draw(batch, text, x + 2, y - 2);

        //Title
        titleFont.getBitmapFont().setColor(Color.WHITE);
        titleFont.getBitmapFont().draw(batch, text, x, y);

        //Character image
        float imageSize = ScreenSetting.TILE_SIZE * 2;
        x = gp.sSetting.SCREEN_WIDTH / 2 - imageSize / 2;
        y = gp.sSetting.SCREEN_HEIGHT / 2 - imageSize / 2;
        batch.draw(Game.player.sprites.down1, x, y, imageSize, imageSize);

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
        return gp.sSetting.SCREEN_WIDTH / 2 - layout.width / 2;

    }

    public void draw() {
        drawTitleScreen();
    }

    public void dispose() {
        font.getBitmapFont().dispose();
        titleFont.getBitmapFont().dispose();
    }

}
