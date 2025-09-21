package io.github.spiritstead.entity.player;

import io.github.spiritstead.collision.EntityCollision;
import io.github.spiritstead.collision.ObjectCollision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

/**
 * This class checks all the different Types of collision that a given entity should check
 */
public class PlayerCollisionHandler {
    GamePanel gp;
    Player player;
    Entity npcs[];

    private TileCollision tileCollision;
    private ObjectCollision objectCollision;
    private EntityCollision entityCollision;

    public PlayerCollisionHandler(GamePanel gp, Player player, Entity npcs[]) {
        this.gp = gp;
        this.player = player;
        this.npcs = npcs;
        this.tileCollision = new TileCollision(gp, player);
        this.objectCollision = new ObjectCollision(gp, player);
        this.entityCollision = new EntityCollision(player, npcs);
    }

    public void checkAll() {
        checkTileCollision();
        checkNPCCollision();
        checkEventCollision();
    }

    private void checkNPCCollision() {
        this.entityCollision.check();
        interactNPC(this.entityCollision.getIndex());

    }

    private void interactNPC(int npcIndex) {
        if (npcIndex != 9999) {
            player.NPCInteraction.setNpc(npcs[npcIndex]);
            player.NPCInteraction.handle();
        }
    }

    private void checkTileCollision() {
        player.collisionOn = false;
        this.tileCollision.check();
        this.objectCollision.check();
        player.playerObjectInteractor.setIndex(this.objectCollision.getIndex());
        player.playerObjectInteractor.interact();

    }

    private void checkEventCollision() {
        gp.system.eHandler.checkEvent();
    }

}
