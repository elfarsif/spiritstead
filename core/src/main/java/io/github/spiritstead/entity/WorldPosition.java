package io.github.spiritstead.entity;

public final class WorldPosition {
    private int x, y;

    public WorldPosition() { }
    public WorldPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public void setXY(int x, int y) {
        this.setX(x); this.setY(y);
    }

}
