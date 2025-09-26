package io.github.spiritstead.entity;

import java.awt.*;

public class SolidArea {
    private Rectangle rect;
    private int defaultX;
    private int defaultY;

    public SolidArea(int x, int y, int width, int height) {
        this.rect = new Rectangle(x, y, width, height);
        this.defaultX = rect.x;
        this.defaultY = rect.y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }
}
