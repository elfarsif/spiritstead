package io.github.spiritstead.entity;

public interface Moveable {
    boolean isCollisionOn();

    int getSpeed();

    SolidArea getSolidArea();

    WorldPosition getWorldPosition();

    Direction getDirection();
}
