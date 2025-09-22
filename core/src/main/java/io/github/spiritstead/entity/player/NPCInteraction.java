package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.PlayerInteractable;
import io.github.spiritstead.main.GamePanel;

public class NPCInteraction {
    GamePanel gp;
    PlayerInteractable npc;

    public NPCInteraction(GamePanel gp) {
        this.gp = gp;

    }

    public void setNpc(PlayerInteractable npc) {
        this.npc = npc;
    }

    public void handle() {
        gp.system.ui.uiScreen = gp.system.ui.dialogueUI;
        gp.screenManager.screen = gp.screenManager.dialogueScreen;
        npc.speak();

    }
}
