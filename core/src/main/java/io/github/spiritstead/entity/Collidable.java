package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Collidable {
    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);

    WorldPosition getWorldPosition();

    int getSpeed();

    Direction getDirection();
}
