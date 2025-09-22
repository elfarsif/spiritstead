package io.github.spiritstead.collision;

import io.github.spiritstead.entity.PlayerInteractable;
import io.github.spiritstead.main.GamePanel;

public class PlayerCollision implements Collision {
    GamePanel gp;
    PlayerInteractable entity;

    public PlayerCollision(GamePanel gp, PlayerInteractable entity) {
        this.gp = gp;
        this.entity = entity;
    }

    public void check() {
        intializeEntitySolidArea();
        initializePlayerSolidArea();
        checkCollisionForAllDirections(entity);
        restoreDefaultSolidAreaValues();

    }

    private void checkCollisionForAllDirections(PlayerInteractable entity) {
        switch (entity.getDirection()) {
            case UP:
                checkUpCollision();
                break;
            case DOWN:
                checkDownCollision();
                break;
            case LEFT:
                checkLeftCollision();
                break;
            case RIGHT:
                checkRightCollision();
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

    private void checkUpCollision() {
        entity.getSolidArea().y += entity.getSpeed();
        if (entity.getSolidArea().intersects(gp.player.solidArea)) {
            entity.setCollisionOn(true);
        }
    }

    private void checkDownCollision() {
        entity.getSolidArea().y -= entity.getSpeed();
        if (entity.getSolidArea().intersects(gp.player.solidArea)) {
            entity.setCollisionOn(true);
        }
    }

    private void checkLeftCollision() {
        entity.getSolidArea().x -= entity.getSpeed();
        if (entity.getSolidArea().intersects(gp.player.solidArea)) {
            entity.setCollisionOn(true);
        }
    }

    private void checkRightCollision() {
        entity.getSolidArea().x += entity.getSpeed();
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
