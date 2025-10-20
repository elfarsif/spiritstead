package io.github.spiritstead.dialogue;

import java.util.ArrayList;

public class NodeBuilder {
    private String text;
    private DialoguePhase phase;
    private Node left;
    private Node right;
    private ArrayList<DialogueEvent> dialogueEvents = new ArrayList<>();

    public NodeBuilder text(String text) { this.text = text; return this; }
    public NodeBuilder phase(DialoguePhase phase) { this.phase = phase; return this; }
    public NodeBuilder left(Node left) { this.left = left; return this; }
    public NodeBuilder right(Node right) { this.right = right; return this; }
    public NodeBuilder addEvent(DialogueEvent event) { this.dialogueEvents.add(event); return this; }
    public NodeBuilder addEvents(ArrayList<DialogueEvent> events) { this.dialogueEvents = dialogueEvents; return this; }

    protected String getText() { return text; }
    protected DialoguePhase getPhase() { return phase; }
    protected Node getLeft() { return left; }
    protected Node getRight() { return right; }
    protected ArrayList<DialogueEvent> getDialogueEvents() { return dialogueEvents; }

    public Node build() { return new Node(this); }

}
