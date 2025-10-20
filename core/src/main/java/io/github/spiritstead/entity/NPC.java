package io.github.spiritstead.entity;

import io.github.spiritstead.dialogue.Node;

public interface NPC extends Collidable, Moveable {
    void interact();

    void draw();

    void update();

    void setDialogueNode(Node node);
}
