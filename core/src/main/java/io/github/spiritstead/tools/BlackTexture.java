package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class BlackTexture {
    public Texture texture;
    int width, height;

    public BlackTexture(int width, int height) {
        this.width = width;
        this.height = height;
        loadBlackBackground();
    }

    private void loadBlackBackground() {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }
}
