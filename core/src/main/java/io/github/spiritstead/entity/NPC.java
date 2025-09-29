package io.github.spiritstead.entity;

import io.github.spiritstead.dialogue.Dialogue;

public interface NPC extends Collidable, Moveable {
    void interact();

    void draw();

    void update();

}
