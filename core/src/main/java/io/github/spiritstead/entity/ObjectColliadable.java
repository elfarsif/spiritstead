package io.github.spiritstead.entity;

public interface ObjectColliadable {
    Direction getDirection();

    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);

    int getSpeed();

    WorldPosition getWorldPosition();

}
