package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.Key;

public class GameScreenUI implements UIScreen {
    Font font;
    Sprite keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public GameScreenUI() {
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
            font.getBitmapFont().draw(Game.batch, message, ScreenSetting.TILE_SIZE, ScreenSetting.SCREEN_HEIGHT - (3 * ScreenSetting.TILE_SIZE));
            font.getBitmapFont().getData().setScale(scale);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    private void drawKeyInventory() {
        Game.batch.draw(keyImage, 10, ScreenSetting.SCREEN_HEIGHT - ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        font.getBitmapFont().draw(Game.batch, Integer.toString(Game.player.hasKey), 2 * ScreenSetting.TILE_SIZE, ScreenSetting.SCREEN_HEIGHT - 10);

    }

    public void draw() {
        drawKeyInventory();
        drawMessages();
        drawDebugUI();
    }

    private void drawDebugUI() {
        if (Game.keyH.tPressed) {
            int x = 10;
            int y = 40;
            int lineHeight = 30;
            font.getBitmapFont().draw(Game.batch, "Player X: " + Game.player.getWorldPosition().getX(), x, y);
            y += lineHeight;
            font.getBitmapFont().draw(Game.batch, "Player Y: " + Game.player.getWorldPosition().getY(), x, y);
            y += lineHeight;
            font.getBitmapFont().draw(Game.batch, "Player Col: " + (Game.player.getWorldPosition().getX() + Game.player.getSolidArea().getRect().x) / ScreenSetting.TILE_SIZE, x, y);
            y += lineHeight;
            font.getBitmapFont().draw(Game.batch, "Player Row: " + (Game.player.getWorldPosition().getY() + Game.player.getSolidArea().getRect().y) / ScreenSetting.TILE_SIZE, x, y);
        }
    }

    public void dispose() {
        font.getBitmapFont().dispose();
    }
}
