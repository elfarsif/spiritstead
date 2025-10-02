package io.github.spiritstead.dialogue;

import io.github.spiritstead.main.Game;

public class Dialogue {
    public DialogueNode node;

    public Dialogue() {
        node = new DialogueNode(Game.script.mayorDialogue.get(0));
        node.left = new DialogueNode(Game.script.mayorDialogue.get(1));
        node.right = new DialogueNode(Game.script.mayorDialogue.get(2));
        node.left.left = new DialogueNode(Game.script.mayorDialogue.get(3), new IncreaseXP());
        node.right.left = new DialogueNode(Game.script.mayorDialogue.get(4), new NoBenifit());

    }

}
