package io.github.spiritstead.entity;

public class Values {
    public Direction direction;
    public int speed;
    public WorldPosition worldPosition = new WorldPosition();

    public WorldPosition getWorldPosition() {
        return worldPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
