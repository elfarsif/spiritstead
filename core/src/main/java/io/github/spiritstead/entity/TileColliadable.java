package io.github.spiritstead.entity;

public interface TileColliadable {
    Direction getDirection();

    int getSpeed();

    boolean isCollisionOn();

    WorldPosition getWorldPosition();

    SolidArea getSolidArea();

    void setCollisonOn(boolean collisonOn);

}
