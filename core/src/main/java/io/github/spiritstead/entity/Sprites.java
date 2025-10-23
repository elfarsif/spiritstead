package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public final class Sprites {
    private final List<Sprite> sprites;
    private final EnumMap<Direction, Sprite[]> frames = new EnumMap<>(Direction.class);

    public Sprites(Sprite... n) {
        this.sprites = new ArrayList<Sprite>();
        Collections.addAll(sprites, n);
        this.frames.put(Direction.UP, new Sprite[] {this.sprites.get(0), this.sprites.get(1)});
        this.frames.put(Direction.DOWN, new Sprite[] {this.sprites.get(2), this.sprites.get(3)});
        this.frames.put(Direction.LEFT, new Sprite[] {this.sprites.get(4), this.sprites.get(5)});
        this.frames.put(Direction.RIGHT, new Sprite[] {this.sprites.get(6), this.sprites.get(7)});
    }

    public Sprite getNextSprite(Direction direction, int spriteNum) {
        return this.frames.get(direction)[spriteNum - 1];
    }

    public List<Sprite> getList() { return this.sprites; }
}
