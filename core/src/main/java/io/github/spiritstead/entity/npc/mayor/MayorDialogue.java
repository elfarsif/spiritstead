package io.github.spiritstead.entity.npc.mayor;

import java.util.HashMap;
import java.util.Map;

public class MayorDialogue {
    public Map<Integer, String> allDialogue = new HashMap<>();
    private int index = 0;
    String currentDialogue;

    public MayorDialogue(Map<Integer, String> allDialogue) {
        this.allDialogue = allDialogue;
    }

    public String getCurrentDialogue() {
        this.currentDialogue = allDialogue.get(this.index);
        this.index++;
        return this.currentDialogue;
    }
}
