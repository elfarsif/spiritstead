package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

/**
 * This class checks all the different Types of collision that a given entity should check
 */
public class EntityCollisionSetChecker {
    GamePanel gp;
    Player player;
    Entity npcs[];

    public EntityCollisionSetChecker(GamePanel gp, Player player, Entity npcs[]) {
        this.gp = gp;
        this.player = player;
        this.npcs = npcs;
    }

    public void checkAll() {
        checkTileCollision();
        checkNPCCollision();
        checkEventCollision();
    }

    private void checkNPCCollision() {
        int npcIndex = gp.system.cChecker.checkPlayerIsCollidingWithEntity(player, npcs);
        interactNPC(npcIndex);

    }

    private void interactNPC(int npcIndex) {
        if (npcIndex != 9999) {
            player.NPCInteraction.setNpc(npcs[npcIndex]);
            player.NPCInteraction.handle();
        }
    }

    private void checkTileCollision() {
        player.collisionOn = false;
        gp.system.cChecker.checkEntityIsCollidingWithCollidableTile(player);
        int objIndex = gp.system.cChecker.checkEntityIsCollidingWithObject(player, true);
        player.playerObjectInteractor.setIndex(objIndex);
        player.playerObjectInteractor.interact();

    }

    private void checkEventCollision() {
        gp.system.eHandler.checkEvent();
    }

}
