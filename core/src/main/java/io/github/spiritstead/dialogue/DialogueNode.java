package io.github.spiritstead.dialogue;

public class DialogueNode {
    public String dialogue;
    public DialogueNode left;
    public DialogueNode right;

    public DialogueNode() {
    }

    public DialogueNode(String dialogue) {
        this.dialogue = dialogue;
    }

    public DialogueNode(String dialogue, DialogueNode left, DialogueNode right) {
        this.dialogue = dialogue;
        this.left = left;
        this.right = right;
    }
}
