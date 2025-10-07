package io.github.spiritstead.dialogue;

import java.util.ArrayList;

public class DialogueNode {
    public String text;
    public DialoguePhase phase;
    public DialogueNode left;
    public DialogueNode right;
    public DialogueNode prev;
    public ArrayList<DialogueEvent> dialogueEvents;

    private DialogueNode(String text) {
        this.text = text;
    }

    public DialogueNode(String text, ArrayList<DialogueEvent> dialogueEvents) {
        this(text);
        this.dialogueEvents = dialogueEvents;
    }

    public DialogueNode(String text, DialoguePhase phase, ArrayList<DialogueEvent> dialogueEvents) {
        this(text, dialogueEvents);
        this.phase = phase;
    }

    public DialogueNode(ArrayList<DialogueEvent> dialogueEvents) {
        this.dialogueEvents = dialogueEvents;
    }

    public DialogueNode nextLeft() {
        if (this.left != null) {
            this.left.prev = this;
        }
        return this.left;
    }

    public DialogueNode nextRight() {
        if (this.right != null) {
            this.right.prev = this;
        }
        return this.right;
    }

    public DialogueNode(String text, DialoguePhase phase) {
        this(text);
        this.phase = phase;
    }

    public void triggerEvent() {
        if (dialogueEvents != null) {
            for (DialogueEvent event : this.dialogueEvents) {
                event.handle();
            }
        }
    }

    public void drawEvent() {
        if (dialogueEvents != null) {

            for (DialogueEvent event : this.dialogueEvents) {
                event.draw();
            }
        }
    }
}
