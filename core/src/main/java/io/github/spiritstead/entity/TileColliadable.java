package io.github.spiritstead.entity;

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
