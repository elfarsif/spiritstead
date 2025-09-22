package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Direction;

import java.awt.*;

public interface TileColliadable {
    Direction getDirection();

    int getSpeed();

    boolean isCollisionOn();

    int getWorldX();

    int getWorldY();

    Rectangle getSolidArea();

    void setCollisonOn(boolean collisonOn);

}
