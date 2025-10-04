package io.github.spiritstead.dialogue;

import java.util.ArrayList;

public class DialogueNode {
    public String text;
    public Dialogue.Phase phase;
    public DialogueNode left;
    public DialogueNode right;
    private ArrayList<DialogueEvent> dialogueEvents;

    public DialogueNode(String text) {
        this.text = text;
    }

    public DialogueNode(String text, ArrayList<DialogueEvent> dialogueEvents) {
        this(text);
        this.dialogueEvents = dialogueEvents;
    }

    public DialogueNode(String text, ArrayList<DialogueEvent> dialogueEvents, Dialogue.Phase phase) {
        this(text, dialogueEvents);
        this.phase = phase;
    }

    public void triggerEvent() {
        for (DialogueEvent event : this.dialogueEvents) {
            event.handle();
        }
    }

    public void drawEvent() {
        for (DialogueEvent event : this.dialogueEvents) {
            event.draw();
        }
    }
}
