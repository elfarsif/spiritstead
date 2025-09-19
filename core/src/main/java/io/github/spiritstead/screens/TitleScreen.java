package io.github.spiritstead.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.BlackTexture;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.UIUtilities;

public class TitleScreen implements Screen {
    private GamePanel gp;
    private BitmapFont font;
    private BitmapFont titleFont;
    private GlyphLayout layout = new GlyphLayout();
    private SpriteBatch batch;
    private BlackTexture blackTexture;

    public int commandNum = 0;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
        font = UIUtilities.initializeFont(font, "fonts/maruMonicaBold.fnt");
        titleFont = UIUtilities.initializeFont(font, "fonts/maruMonicaBold.fnt");
        this.blackTexture = new BlackTexture(gp.sSetting.screenWidth, gp.sSetting.screenHeight);
        this.batch = gp.batch;
    }

    private void drawTitleScreen() {
        batch.draw(blackTexture.texture, 0, 0);

        //TITLE NAME placment
        titleFont.getData().setScale(3f);
        String text = "Spiritstead";
        float x = getXforCenteredText(titleFont, text);
        float y = gp.sSetting.screenHeight * 3 / 4 + layout.height / 2;

        //Shadow
        titleFont.setColor(Color.GRAY);
        titleFont.draw(batch, text, x + 3, y - 3);

        //Title
        titleFont.setColor(Color.WHITE);
        titleFont.draw(batch, text, x, y);

        //Character image
        float imageSize = gp.sSetting.tileSize * 2;
        x = gp.sSetting.screenWidth / 2 - imageSize / 2;
        y = gp.sSetting.screenHeight / 2 - imageSize / 2;
        batch.draw(gp.player.down1, x, y, imageSize, imageSize);

        //MENU
        font.getData().setScale(1f);

        text = "NEW GAME";
        x = getXforCenteredText(font, text);
        y -= gp.sSetting.tileSize;
        font.draw(batch, text, x, y);
        if (commandNum == TitleScreenOptions.NEW_GAME.getValue()) {
            font.draw(batch, ">", x - gp.sSetting.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(font, text);
        y -= gp.sSetting.tileSize;
        font.draw(batch, text, x, y);
        if (commandNum == TitleScreenOptions.LOAD_GAME.getValue()) {
            font.draw(batch, ">", x - gp.sSetting.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(font, text);
        y -= gp.sSetting.tileSize;
        font.draw(batch, text, x, y);
        if (commandNum == TitleScreenOptions.QUIT.getValue()) {
            font.draw(batch, ">", x - gp.sSetting.tileSize, y);
        }

    }

    private float getXforCenteredText(BitmapFont font, String text) {
        layout.setText(font, text);
        return gp.sSetting.screenWidth / 2 - layout.width / 2;

    }

    public void draw() {
        drawTitleScreen();
    }

    public void dispose() {
        font.dispose();
        titleFont.dispose();
    }

}
