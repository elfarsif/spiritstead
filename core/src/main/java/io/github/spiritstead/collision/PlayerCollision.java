package io.github.spiritstead.collision;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.Game;
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
        switch (entity.getValues().getDirection()) {
            case UP:
                entity.getSolidArea().getRect().y += entity.getValues().getSpeed();
                checkCollision();
                break;
            case DOWN:
                entity.getSolidArea().getRect().y -= entity.getValues().getSpeed();
                checkCollision();
                break;
            case LEFT:
                entity.getSolidArea().getRect().x -= entity.getValues().getSpeed();
                checkCollision();
                break;
            case RIGHT:
                entity.getSolidArea().getRect().x += entity.getValues().getSpeed();
                checkCollision();
                break;
        }
    }

    private void intializeEntitySolidArea() {
        entity.getSolidArea().getRect().x = entity.getValues().getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entity.getSolidArea().getRect().y = entity.getValues().getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }

    private void initializePlayerSolidArea() {
        Game.player.getSolidArea().getRect().x = Game.player.values.getWorldPosition().getX() + Game.player.getSolidArea().getRect().x;
        Game.player.getSolidArea().getRect().y = Game.player.values.getWorldPosition().getY() + Game.player.getSolidArea().getRect().y;
    }

    private void checkCollision() {
        if (entity.getSolidArea().getRect().intersects(Game.player.getSolidArea().getRect())) {
            entity.setCollisionOn(true);
        }
    }

    private void restoreDefaultSolidAreaValues() {
        entity.getSolidArea().getRect().x = entity.getSolidArea().getDefaultX();
        entity.getSolidArea().getRect().y = entity.getSolidArea().getDefaultY();
        Game.player.getSolidArea().getRect().x = Game.player.getSolidArea().getDefaultX();
        Game.player.getSolidArea().getRect().y = Game.player.getSolidArea().getDefaultY();
    }
}
