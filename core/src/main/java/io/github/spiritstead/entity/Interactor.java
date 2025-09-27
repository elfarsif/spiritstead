package io.github.spiritstead.entity;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.Game;

public class Interactor {
    private Player player;
    private NPC npc;

    public Interactor(Player player) {
        this.player = player;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void interact() {
        changeGameState();
        npc.speak();
    }

    private void changeGameState() {
        Game.ui.uiScreen = Game.ui.dialogueUI;
        Game.screens.screen = Game.screens.dialogueScreen;
    }

}
