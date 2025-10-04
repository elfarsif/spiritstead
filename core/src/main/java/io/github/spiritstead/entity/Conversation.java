package io.github.spiritstead.entity;

import io.github.spiritstead.dialogue.Dialogue;
import io.github.spiritstead.dialogue.DialogueNode;
import io.github.spiritstead.main.Game;

public class Conversation {
    public void start(DialogueNode dialogueNode) {
        if (dialogueNode.phase == Dialogue.Phase.STARTING) {
            Game.ui.dialogueUI.text.currentDialogue = dialogueNode.text;
        }
    }
}
