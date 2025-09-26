package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.EnumMap;

public class Sprites {
    public Sprite up1, up2, down1, down2, left1, left2, right1, right2;
    public EnumMap<Direction, Sprite[]> frames = new EnumMap<>(Direction.class);

    public void load() {
        up1 = new Sprite(new Texture("player/up1.png"));
        up2 = new Sprite(new Texture("player/up2.png"));
        down1 = new Sprite(new Texture("player/down1.png"));
        down2 = new Sprite(new Texture("player/down2.png"));
        left1 = new Sprite(new Texture("player/left1.png"));
        left2 = new Sprite(new Texture("player/left2.png"));
        right1 = new Sprite(new Texture("player/right1.png"));
        right2 = new Sprite(new Texture("player/right2.png"));
        frames.put(Direction.UP, new Sprite[]{up1, up2});
        frames.put(Direction.DOWN, new Sprite[]{down1, down2});
        frames.put(Direction.LEFT, new Sprite[]{left1, left2});
        frames.put(Direction.RIGHT, new Sprite[]{right1, right2});
    }
}
