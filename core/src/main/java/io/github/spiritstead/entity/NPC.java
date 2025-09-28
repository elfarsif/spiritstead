package io.github.spiritstead.entity;

public interface NPC extends Collidable, Moveable {
    void interact();

    void draw();

    void update();

}
