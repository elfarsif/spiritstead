package io.github.spiritstead.entity;

public interface Collidable {
    SolidArea getSolidArea();

    WorldPosition getWorldPosition();

    int getSpeed();

    Direction getDirection();
}
