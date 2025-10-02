package io.github.spiritstead.dialogue;

public class DialogueController {
    public enum Phase {STARTING, CHOOSING, CHOOSINGEFFECT, ADVANCING, ENDING}

    public Phase phase;

    public DialogueController() {
        this.phase = Phase.STARTING;
    }
}
