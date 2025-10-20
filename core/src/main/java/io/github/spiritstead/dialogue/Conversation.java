package io.github.spiritstead.dialogue;

public class Conversation {
    private final Node node;
    private Node temp;
    private boolean hasStarted = false;
    private boolean isMakigChoice = false;
    private String line;

    public Conversation(Node node) {
        this.node = node;
        this.temp = node;
    }

    private void start() { this.line = this.node.getText(); hasStarted = true; }

    public void makeChoice(String node) {
        if (node.equals("left")) {
            this.temp = this.temp.getLeft();
            this.isMakigChoice = true;
        } else if (node.equals("right")) {
            this.temp = this.temp.getRight();
            this.isMakigChoice = true;
        }
        this.line = this.temp.getText();
    }

    public String getLine() { return this.line; }
    public void proceed() {
        if (! hasStarted) {
            this.start();
        } else if (isMakigChoice) {
            this.line = this.temp.getText();
            this.isMakigChoice = false;

        } else {
            this.temp = this.temp.nextLeft();
            this.line = this.temp.getText();
        }
    }
}
