package io.github.spiritstead.entity.player.updator;

import io.github.spiritstead.collision.Collision2;
import io.github.spiritstead.collision.ObjectCollision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.entity.Moveable;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.Game;

/**
 * This class checks all the different Types of collision that a given entity should check
 */
public class PlayerCollisionHandler {
    Player player;
    NPC npcs[];

    private Collision2 collision2;
    private TileCollision tileCollision;
    private ObjectCollision objectCollision;

    public PlayerCollisionHandler(Player player, NPC npcs[]) {
        this.player = player;
        this.npcs = npcs;
        this.collision2 = new Collision2();
        this.tileCollision = new TileCollision(Game.tileM, player);
        this.objectCollision = new ObjectCollision(player);
    }

    public void checkAll() {
        checkTileCollision();
        checkEventCollision();
    }

    public void checkNPCCollision() {
        for (int i = 0; i < npcs.length - 9; i++) {
            if (collision2.check(this.player, npcs[i])) {
                interactNPC(i);
            }
        }

    }

    private void interactNPC(int npcIndex) {
        player.interact(npcs[npcIndex]);
    }

    public void checkTileCollision() {
        this.tileCollision.check();
        this.objectCollision.check();
        player.interactObject(this.objectCollision.getIndex());

    }

    private void checkEventCollision() {
        Game.eHandler.checkEvent();
    }

}
