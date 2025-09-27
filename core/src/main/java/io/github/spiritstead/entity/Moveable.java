package io.github.spiritstead.entity;

public interface Moveable {
    boolean isCollisionOn();

    int getSpeed();

    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);

    WorldPosition getWorldPosition();

    Direction getDirection();
}
