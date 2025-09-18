package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;

public class EntityRenderer {
    private GamePanel gp;
    SpriteBatch batch;
    Player player;

    public EntityRenderer(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.batch = gp.batch;
    }

    public void draw() {
        Sprite currentSprite = null;
        switch (player.direction) {
            case UP:
                if (player.spriteNum == 1) {
                    currentSprite = player.up1;
                } else if (player.spriteNum == 2) {
                    currentSprite = player.up2;
                }
                break;
            case DOWN:
                if (player.spriteNum == 1) {
                    currentSprite = player.down1;
                } else if (player.spriteNum == 2) {
                    currentSprite = player.down2;
                }
                break;
            case LEFT:
                if (player.spriteNum == 1) {
                    currentSprite = player.left1;
                } else if (player.spriteNum == 2) {
                    currentSprite = player.left2;
                }
                break;
            case RIGHT:
                if (player.spriteNum == 1) {
                    currentSprite = player.right1;
                } else if (player.spriteNum == 2) {
                    currentSprite = player.right2;
                }
                break;
            default:
                currentSprite = player.down1;
        }
        batch.draw(currentSprite, player.screenX, player.screenY, gp.sSetting.tileSize, gp.sSetting.tileSize);
    }
}
