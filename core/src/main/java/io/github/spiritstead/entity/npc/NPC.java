package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.entity.Values;

import java.util.EnumMap;

public interface NPC {
    void speak();

    SolidArea getSolidArea();

    Values getValues();

    void setCollisionOn(boolean collisionOn);

    int getSpriteNum();

    void setDirection(Direction direction);

    boolean isCollisionOn();

    void draw();

    void update();

}
