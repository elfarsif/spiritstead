package io.github.spiritstead.entity;

import java.awt.*;

public class SolidArea {
    private final Rectangle rect;
    private final int defaultX;
    private final int defaultY;

    public SolidArea(int x, int y, int width, int height) {
        this.rect = new Rectangle(x, y, width, height);
        this.defaultX = rect.x;
        this.defaultY = rect.y;
    }

    public Rectangle getRect() { return rect; }
    public int getDefaultX() { return defaultX; }
    public int getDefaultY() { return defaultY; }

}
