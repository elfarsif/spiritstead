package io.github.spiritstead.dialogue;

public class DialogueNode {
    public String dialogue;
    private DialogueEvent dialogueEvent;
    public DialogueNode left;
    public DialogueNode right;

    public DialogueNode(String dialogue) {
        this.dialogue = dialogue;
    }

    public DialogueNode(String dialogue, DialogueEvent dialogueEvent) {
        this(dialogue);
        this.dialogueEvent = dialogueEvent;
    }

    public void triggerEvent() {
        dialogueEvent.handle();
    }

    public void drawEvent() {
        dialogueEvent.draw();
    }
}
