package io.github.spiritstead.entity;

public interface ObjectColliadable {
    boolean isPlayer();

    Direction getDirection();

    void setSolidArea(SolidArea solidArea);

    SolidArea getSolidArea();

    boolean isCollisionOn();

    void setCollisionOn(boolean collisionOn);

    int getSpeed();

    WorldPosition getWorldPosition();

}
