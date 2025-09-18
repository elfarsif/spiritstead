package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class NPCInteraction {
    GamePanel gp;
    Entity npc;

    public NPCInteraction(GamePanel gp) {
        this.gp = gp;

    }

    public void setNpc(Entity npc) {
        this.npc = npc;
    }

    public void handle() {
        gp.system.ui.uiScreen = gp.system.ui.dialogueUI;
        gp.screenManager.screen = gp.screenManager.dialogueScreen;
        npc.speak();

    }
}
