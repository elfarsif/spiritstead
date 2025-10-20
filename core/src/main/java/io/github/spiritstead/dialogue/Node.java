package io.github.spiritstead.dialogue;

import java.util.ArrayList;

public class Node {
    private final String text;
    private final DialoguePhase phase;
    private final ArrayList<DialogueEvent> dialogueEvents;
    private final Node left;
    private final Node right;
    private Node prev;

    public static NodeBuilder builder() { return new NodeBuilder(); }

    protected Node(NodeBuilder builder) {
        this.text = builder.getText();
        this.phase = builder.getPhase();
        this.left = builder.getLeft();
        this.right = builder.getRight();
        this.dialogueEvents = builder.getDialogueEvents();
    }

    public Node nextLeft() {
        if (this.left != null) {
            this.left.prev = this;
        }
        return this.left;
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
    public ArrayList<DialogueEvent> getDialogueEvents() { return this.dialogueEvents; }
    public Node getPrev() { return this.prev; }
    public Node getRight() { return this.right; }
    public Node getLeft() { return this.left; }
    public String getText() { return this.text; }
    public boolean isPhase(DialoguePhase phase) { return this.phase == phase; }
}
