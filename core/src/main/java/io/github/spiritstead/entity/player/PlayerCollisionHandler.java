package io.github.spiritstead.entity.player;

import io.github.spiritstead.collision.NPCCollision;
import io.github.spiritstead.collision.ObjectCollision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

/**
 * This class checks all the different Types of collision that a given entity should check
 */
public class PlayerCollisionHandler {
    Player player;
    NPC npcs[];

    private TileCollision tileCollision;
    private ObjectCollision objectCollision;
    private NPCCollision NPCCollision;

    public PlayerCollisionHandler(Player player, NPC npcs[]) {
        this.player = player;
        this.npcs = npcs;
        this.tileCollision = new TileCollision(Game.tileM, player);
        this.objectCollision = new ObjectCollision(player);
        this.NPCCollision = new NPCCollision(player, npcs);
    }

    public void checkAll() {
        checkTileCollision();
        checkNPCCollision();
        checkEventCollision();
    }

    private void checkNPCCollision() {
        this.NPCCollision.check();
        interactNPC(this.NPCCollision.getIndex());

    }

    private void interactNPC(int npcIndex) {
        if (npcIndex != 9999) {
            player.interact(npcs[npcIndex]);
        }
    }

    private void checkTileCollision() {
        player.collisionOn = false;
        this.tileCollision.check();
        this.objectCollision.check();
        player.interactObject(this.objectCollision.getIndex());

    }

    private void checkEventCollision() {
        Game.eHandler.checkEvent();
    }

}
