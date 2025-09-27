package io.github.spiritstead.entity.npc;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.Values;

public interface NPC extends Collidable {
    void speak();

    SolidArea getSolidArea();

    Values getValues();

    void setCollisionOn(boolean collisionOn);

    int getSpriteNum();

    void setDirection(Direction direction);

    void draw();

    void update();

}
