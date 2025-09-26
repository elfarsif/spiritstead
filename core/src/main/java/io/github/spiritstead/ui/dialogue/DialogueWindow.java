package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

/**
 * This class builds a window with a border, with specific size and placement
 */
public class DialogueWindow {
    private GamePanel gp;
    private SpriteBatch batch;
    private Pixmap pixmap;
    Texture texture;
    int x, y, height;
    private int width;

    public DialogueWindow(GamePanel gp) {
        this.gp = gp;
        this.batch = Game.batch;
        initializeDimensions();
        createWindowPixmap();
    }

    private void initializeDimensions() {
        x = ScreenSetting.TILE_SIZE * 2;
        y = ScreenSetting.TILE_SIZE / 2;
        width = gp.sSetting.SCREEN_WIDTH - ScreenSetting.TILE_SIZE * 4;
        height = ScreenSetting.TILE_SIZE * 3;

    }

    private void createWindowPixmap() {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        pixmap.setColor(0, 0, 0, 0.75f);
        pixmap.fill();
        drawWindowBorder();

        texture = new Texture(pixmap);

        pixmap.dispose();
    }

    private void drawWindowBorder() {
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(3, 3, width - 6, height - 6);
    }

    public void draw() {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        pixmap.dispose();
    }

    public int getWidth() {
        return width;
    }

}
