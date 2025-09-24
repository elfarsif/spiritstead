package io.github.spiritstead.collision;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.GamePanel;

public class PlayerCollision implements Collision {
    GamePanel gp;
    NPC entity;

    public PlayerCollision(GamePanel gp, NPC entity) {
        this.gp = gp;
        this.entity = entity;
    }

    public void check() {
        intializeEntitySolidArea();
        initializePlayerSolidArea();
        checkCollisionForAllDirections(entity);
        restoreDefaultSolidAreaValues();

    }

    private void checkCollisionForAllDirections(NPC entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.getSolidArea().y += entity.getSpeed();
                checkCollision();
                break;
            case DOWN:
                entity.getSolidArea().y -= entity.getSpeed();
                checkCollision();
                break;
            case LEFT:
                entity.getSolidArea().x -= entity.getSpeed();
                checkCollision();
                break;
            case RIGHT:
                entity.getSolidArea().x += entity.getSpeed();
                checkCollision();
                break;
        }
    }

    private void intializeEntitySolidArea() {
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
    }

    private void initializePlayerSolidArea() {
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
    }

    private void checkCollision() {
        if (entity.getSolidArea().intersects(gp.player.solidArea)) {
            entity.setCollisionOn(true);
        }
    }

    private void restoreDefaultSolidAreaValues() {
        entity.getSolidArea().x = entity.getSolidAreadDefaultX();
        entity.getSolidArea().y = entity.getSolidAreadDefaultY();
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
