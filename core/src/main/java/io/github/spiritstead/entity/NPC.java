package io.github.spiritstead.entity;

public interface NPC extends Collidable, Moveable {
    void speak();

    void draw();

    void update();

}
