package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class NPCDrawer {
    private GamePanel gp;
    SpriteBatch batch;
    Sprite sprite;
    NPC npc;
    int screenX, screenY;

    public NPCDrawer(GamePanel gp, NPC npc) {
        this.gp = gp;
        this.npc = npc;
        this.batch = gp.batch;
    }

    public void draw() {
        initialiazeScreenPositionRelativeToPlayer();
        if (entityIsWithinScreenBounds()) {
            updateSprite();

            batch.draw(sprite, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

        }
    }

    private void updateSprite() {
        sprite = null;
        switch (npc.getDirection()) {
            case UP:
                sprite = npc.getFrames().get(Direction.UP)[npc.getSpriteNum() - 1];
                break;
            case DOWN:
                sprite = npc.getFrames().get(Direction.DOWN)[npc.getSpriteNum() - 1];
                break;
            case LEFT:
                sprite = npc.getFrames().get(Direction.LEFT)[npc.getSpriteNum() - 1];
                break;
            case RIGHT:
                sprite = npc.getFrames().get(Direction.RIGHT)[npc.getSpriteNum() - 1];
                break;
            default:
                sprite = npc.getDown1();
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return npc.getWorldX() + ScreenSetting.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
            npc.getWorldX() - ScreenSetting.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
            npc.getWorldY() + ScreenSetting.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
            npc.getWorldY() - ScreenSetting.TILE_SIZE < gp.player.worldY + gp.player.screenY;
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        screenX = npc.getWorldX() - gp.player.worldX + gp.player.screenX;
        screenY = npc.getWorldY() - gp.player.worldY + gp.player.screenY;
    }
}
