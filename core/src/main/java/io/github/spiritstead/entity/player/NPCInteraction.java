package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.GamePanel;

public class NPCInteraction {
    GamePanel gp;
    NPC npc;

    public NPCInteraction(GamePanel gp) {
        this.gp = gp;

    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void handle() {
        gp.system.ui.uiScreen = gp.system.ui.dialogueUI;
        gp.screenManager.screen = gp.screenManager.dialogueScreen;
        npc.speak();

    }
}
