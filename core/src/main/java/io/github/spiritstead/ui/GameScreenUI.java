package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.object.Key;

public class GameScreenUI implements UIScreen {

    GamePanel gp;
    Font font;
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
        font = new Font("fonts/maruMonicaBold.fnt");

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
            float scale = font.getBitmapFont().getScaleX();
            font.getBitmapFont().getData().setScale(0.75f);
            font.getBitmapFont().draw(batch, message, ScreenSetting.TILE_SIZE, gp.sSetting.SCREEN_HEIGHT - (3 * ScreenSetting.TILE_SIZE));
            font.getBitmapFont().getData().setScale(scale);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    private void drawKeyInventory() {
        batch.draw(keyImage, 10, gp.sSetting.SCREEN_HEIGHT - ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        font.getBitmapFont().draw(batch, Integer.toString(gp.player.hasKey), 2 * ScreenSetting.TILE_SIZE, gp.sSetting.SCREEN_HEIGHT - 10);

    }

    public void draw() {
        drawKeyInventory();
        drawMessages();
        if (gp.system.keyH.tPressed) {
            int x = 10;
            int y = 40;
            int lineHeight = 30;
            font.getBitmapFont().draw(batch, "Player X: " + gp.player.worldX, x, y);
            y += lineHeight;
            font.getBitmapFont().draw(batch, "Player Y: " + gp.player.worldY, x, y);
            y += lineHeight;
            font.getBitmapFont().draw(batch, "Player Col: " + (gp.player.worldX + gp.player.solidArea.x) / ScreenSetting.TILE_SIZE, x, y);
            y += lineHeight;
            font.getBitmapFont().draw(batch, "Player Row: " + (gp.player.worldY + gp.player.solidArea.y) / ScreenSetting.TILE_SIZE, x, y);
        }
    }

    public void dispose() {
        font.getBitmapFont().dispose();
    }
}
