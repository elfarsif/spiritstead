package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.Key;

public class GameScreenUI implements UIScreen {

    GamePanel gp;
    BitmapFont font;
    Sprite keyImage;
    SpriteBatch batch;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public GameScreenUI(GamePanel gp) {
        this.gp = gp;
        this.batch = gp.batch;
        loadKeyImage();
        font = UIUtilities.initializeFont(font, "fonts/maruMonicaBold.fnt");

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    private void loadKeyImage() {
        Key key = new Key();
        keyImage = key.image;
    }

    private void drawMessages() {
        if (messageOn) {
            float scale = font.getScaleX();
            font.getData().setScale(0.75f);
            font.draw(batch, message, gp.sSetting.tileSize, gp.sSetting.screenHeight - (3 * gp.sSetting.tileSize));
            font.getData().setScale(scale);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    private void drawKeyInventory() {
        batch.draw(keyImage, 10, gp.sSetting.screenHeight - gp.sSetting.tileSize, gp.sSetting.tileSize, gp.sSetting.tileSize);
        font.draw(batch, Integer.toString(gp.player.hasKey), 2 * gp.sSetting.tileSize, gp.sSetting.screenHeight - 10);

    }

    public void draw() {
        drawKeyInventory();
        drawMessages();
        if (gp.system.keyH.tPressed) {
            int x = 10;
            int y = 40;
            int lineHeight = 30;
            font.draw(batch, "Player X: " + gp.player.worldX, x, y);
            y += lineHeight;
            font.draw(batch, "Player Y: " + gp.player.worldY, x, y);
            y += lineHeight;
            font.draw(batch, "Player Col: " + (gp.player.worldX + gp.player.solidArea.x) / gp.sSetting.tileSize, x, y);
            y += lineHeight;
            font.draw(batch, "Player Row: " + (gp.player.worldY + gp.player.solidArea.y) / gp.sSetting.tileSize, x, y);
        }
    }

    public void dispose() {
        font.dispose();
    }
}
