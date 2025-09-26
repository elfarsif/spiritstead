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
                entity.getSolidArea().getRect().y += entity.getSpeed();
                checkCollision();
                break;
            case DOWN:
                entity.getSolidArea().getRect().y -= entity.getSpeed();
                checkCollision();
                break;
            case LEFT:
                entity.getSolidArea().getRect().x -= entity.getSpeed();
                checkCollision();
                break;
            case RIGHT:
                entity.getSolidArea().getRect().x += entity.getSpeed();
                checkCollision();
                break;
        }
    }

    private void intializeEntitySolidArea() {
        entity.getSolidArea().getRect().x = entity.getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entity.getSolidArea().getRect().y = entity.getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }

    private void initializePlayerSolidArea() {
        gp.player.getSolidArea().getRect().x = gp.player.getWorldPosition().getX() + gp.player.getSolidArea().getRect().x;
        gp.player.getSolidArea().getRect().y = gp.player.getWorldPosition().getY() + gp.player.getSolidArea().getRect().y;
    }

    private void checkCollision() {
        if (entity.getSolidArea().getRect().intersects(gp.player.getSolidArea().getRect())) {
            entity.setCollisionOn(true);
        }
    }

    private void restoreDefaultSolidAreaValues() {
        entity.getSolidArea().getRect().x = entity.getSolidArea().getDefaultX();
        entity.getSolidArea().getRect().y = entity.getSolidArea().getDefaultY();
        gp.player.getSolidArea().getRect().x = gp.player.getSolidArea().getDefaultX();
        gp.player.getSolidArea().getRect().y = gp.player.getSolidArea().getDefaultY();
    }
}
