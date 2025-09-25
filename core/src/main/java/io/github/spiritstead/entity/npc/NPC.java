package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.player.WorldPosition;

import java.awt.*;
import java.util.EnumMap;

public interface NPC {
    void speak();

    Rectangle getSolidArea();

    WorldPosition getWorldPosition();

    int getSolidAreadDefaultX();

    int getSolidAreadDefaultY();

    Direction getDirection();

    void setCollisionOn(boolean collisionOn);

    int getSpeed();

    int getSpriteNum();

    Sprite getDown1();

    void setUp1(Sprite up1);

    void setUp2(Sprite up2);

    void setDown1(Sprite down1);

    void setDown2(Sprite down2);

    void setLeft1(Sprite left1);

    void setLeft2(Sprite left2);

    void setRight1(Sprite right1);

    void setRight2(Sprite right2);

    void setDirection(Direction direction);

    boolean isCollisionOn();

    EnumMap<Direction, Sprite[]> getFrames();

    void draw();

    void update();

}
