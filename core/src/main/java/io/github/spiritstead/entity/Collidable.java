package io.github.spiritstead.entity;

public interface Collidable {
    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);

    WorldPosition getWorldPosition();

    int getSpeed();

    Direction getDirection();
}
