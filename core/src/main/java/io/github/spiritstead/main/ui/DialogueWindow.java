package io.github.spiritstead.main.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.GamePanel;

/**
 * This class builds a window with a border, with specific size and placement
 */
public class DialogueWindow {
    private GamePanel gp;
    private Pixmap pixmap;
    Texture texture;
    int x, y, width, height;

    public DialogueWindow(GamePanel gp) {
        this.gp = gp;
        initializeDimensions();
        createWindowPixmap();
    }

    private void initializeDimensions() {
        x = gp.sSetting.tileSize * 2;
        y = gp.sSetting.tileSize / 2;
        width = gp.sSetting.screenWidth - gp.sSetting.tileSize * 4;
        height = gp.sSetting.tileSize * 3;

    }

    private void createWindowPixmap() {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        drawWindowBorder();

        texture = new Texture(pixmap);

        pixmap.dispose();
    }

    private void drawWindowBorder() {
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(3, 3, width - 6, height - 6);
    }

}
