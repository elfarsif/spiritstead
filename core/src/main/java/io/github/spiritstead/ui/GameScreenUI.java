package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.Key;

public class GameScreenUI implements UIScreen {
    Font font;
    Sprite inventoryBar, inventoryScroller;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public GameScreenUI() {
        this.inventoryBar = new Sprite(new Texture("objects/inventory.png"));
        font = new Font("fonts/maruMonicaBold.fnt");
        generateInventoryScroller();

    }
    private void generateInventoryScroller() {
        Pixmap pixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        this.inventoryScroller = new Sprite(new Texture(pixmap));
        pixmap.dispose();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
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

    private void drawInventory() {
        Game.batch.draw(inventoryBar,
                ScreenSetting.SCREEN_WIDTH / 2 - inventoryBar.getWidth() * ScreenSetting.SCALE / 2,
                ScreenSetting.TILE_SIZE,
                inventoryBar.getWidth() * ScreenSetting.SCALE,
                inventoryBar.getHeight() * ScreenSetting.SCALE);

        float x = (ScreenSetting.SCREEN_WIDTH / 2 - inventoryBar.getWidth() * ScreenSetting.SCALE / 2) + 3;
        for (int i = 0; i < Game.player.inventory.items.size(); i++) {
            int y = ScreenSetting.TILE_SIZE + 3 * ScreenSetting.SCALE;
            Game.batch.draw(Game.player.inventory.items.get(i).getImage(),
                    x,
                    ScreenSetting.TILE_SIZE + 3 * ScreenSetting.SCALE,
                    ScreenSetting.TILE_SIZE,
                    ScreenSetting.TILE_SIZE
            );
            if (Game.player.inventory.items.get(i) == Game.player.inventory.selectedItem) {
                Game.batch.draw(this.inventoryScroller, x, y);
            }
            x += ScreenSetting.TILE_SIZE;
        }
    }

    public void draw() {
        drawInventory();
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
