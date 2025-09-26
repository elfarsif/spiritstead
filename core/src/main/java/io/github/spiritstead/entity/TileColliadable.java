package io.github.spiritstead.entity;

public interface TileColliadable {
    Direction getDirection();

    int getSpeed();

    WorldPosition getWorldPosition();

    SolidArea getSolidArea();

    void setCollisionOn(boolean collisonOn);

}
