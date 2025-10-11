package io.github.spiritstead.entity;

import io.github.spiritstead.dialogueTree.DialogueNode;

public interface NPC extends Collidable, Moveable {
    void interact();

    void draw();

    void update();

    void setDialogueNode(DialogueNode dialogueNode);
}
