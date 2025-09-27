package io.github.spiritstead.entity;

public interface Moveable {
    boolean isCollisionOn();

    Values getValues();

    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);
}
