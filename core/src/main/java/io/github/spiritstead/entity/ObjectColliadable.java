package io.github.spiritstead.entity;

import io.github.spiritstead.entity.player.WorldPosition;

import java.awt.*;

public interface ObjectColliadable {
    boolean isPlayer();

    Direction getDirection();

    void setSolidArea(Rectangle solidArea);

    Rectangle getSolidArea();

    boolean isCollisionOn();

    void setCollisionOn(boolean collisionOn);

    int getSpeed();

    WorldPosition getWorldPosition();

    int getSolidAreadDefaultX();

    int getSolidAreadDefaultY();

}
