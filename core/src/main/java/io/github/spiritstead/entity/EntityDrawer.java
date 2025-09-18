package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;

public class EntityDrawer {
    private GamePanel gp;
    SpriteBatch batch;
    Entity entity;
    int screenX, screenY;

    public EntityDrawer(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;
        this.batch = gp.batch;
    }

    public void draw() {
        //Calculate where on the screen to draw the tile relative to the player
        screenX = entity.worldX - gp.player.worldX + gp.player.screenX;
        screenY = entity.worldY - gp.player.worldY + gp.player.screenY;

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if ((entity instanceof Player) ||
            entity.worldX + gp.sSetting.tileSize > gp.player.worldX - gp.player.screenX &&
                entity.worldX - gp.sSetting.tileSize < gp.player.worldX + gp.player.screenX &&
                entity.worldY + gp.sSetting.tileSize > gp.player.worldY - gp.player.screenY &&
                entity.worldY - gp.sSetting.tileSize < gp.player.worldY + gp.player.screenY) {

            Sprite currentSprite = null;
            switch (entity.direction) {
                case UP:
                    if (entity.spriteNum == 1) {
                        currentSprite = entity.up1;
                    } else if (entity.spriteNum == 2) {
                        currentSprite = entity.up2;
                    }
                    break;
                case DOWN:
                    if (entity.spriteNum == 1) {
                        currentSprite = entity.down1;
                    } else if (entity.spriteNum == 2) {
                        currentSprite = entity.down2;
                    }
                    break;
                case LEFT:
                    if (entity.spriteNum == 1) {
                        currentSprite = entity.left1;
                    } else if (entity.spriteNum == 2) {
                        currentSprite = entity.left2;
                    }
                    break;
                case RIGHT:
                    if (entity.spriteNum == 1) {
                        currentSprite = entity.right1;
                    } else if (entity.spriteNum == 2) {
                        currentSprite = entity.right2;
                    }
                    break;
                default:
                    currentSprite = entity.down1;
            }
            if (entity instanceof Player) {
                batch.draw(currentSprite, entity.screenX, entity.screenY, gp.sSetting.tileSize, gp.sSetting.tileSize);
            } else {
                batch.draw(currentSprite, screenX, screenY, gp.sSetting.tileSize, gp.sSetting.tileSize);
            }
        }
    }
}
