package io.github.spiritstead.entity;

import java.awt.*;

public interface ObjectColliadable {
    boolean isPlayer();

    Direction getDirection();

    void setSolidArea(Rectangle solidArea);

    Rectangle getSolidArea();

    boolean isCollisionOn();

    void setCollisionOn(boolean collisionOn);

    int getSpeed();

    int getSolidAreadDefaultX();

    int getSolidAreadDefaultY();

    int getWorldX();

    int getWorldY();
}
