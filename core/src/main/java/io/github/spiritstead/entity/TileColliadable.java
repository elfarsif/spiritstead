package io.github.spiritstead.entity;

import io.github.spiritstead.entity.player.WorldPosition;

import java.awt.*;

public interface TileColliadable {
    Direction getDirection();

    int getSpeed();

    boolean isCollisionOn();

    WorldPosition getWorldPosition();

    Rectangle getSolidArea();

    void setCollisonOn(boolean collisonOn);

}
