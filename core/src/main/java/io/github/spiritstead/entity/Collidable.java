package io.github.spiritstead.entity;

public interface Collidable {
    Values getValues();

    SolidArea getSolidArea();

    void setCollisionOn(boolean collisionOn);

}
