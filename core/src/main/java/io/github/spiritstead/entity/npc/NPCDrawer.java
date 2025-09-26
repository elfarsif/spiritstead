package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class NPCDrawer {
    private GamePanel gp;
    private SpriteBatch batch;
    private Sprite sprite;
    private NPC npc;
    private int screenX, screenY;

    public NPCDrawer(GamePanel gp, NPC npc) {
        this.gp = gp;
        this.npc = npc;
        this.batch = Game.batch;
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
        return npc.getWorldPosition().getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            npc.getWorldPosition().getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            npc.getWorldPosition().getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            npc.getWorldPosition().getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getY();
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        this.screenX = npc.getWorldPosition().getX() - Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX();
        this.screenY = npc.getWorldPosition().getY() - Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY();
    }
}
