package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.Sprites;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class NPCDrawer {
    private GamePanel gp;
    private SpriteBatch batch;
    private Sprite sprite;
    private NPC npc;
    private int screenX, screenY;
    private Sprites sprites;

    public NPCDrawer(GamePanel gp, NPC npc, Sprites sprites) {
        this.gp = gp;
        this.npc = npc;
        this.batch = Game.batch;
        this.sprites = sprites;
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
        switch (npc.getValues().getDirection()) {
            case UP:
                sprite = sprites.frames.get(Direction.UP)[npc.getSpriteNum() - 1];
                break;
            case DOWN:
                sprite = sprites.frames.get(Direction.DOWN)[npc.getSpriteNum() - 1];
                break;
            case LEFT:
                sprite = sprites.frames.get(Direction.LEFT)[npc.getSpriteNum() - 1];
                break;
            case RIGHT:
                sprite = sprites.frames.get(Direction.RIGHT)[npc.getSpriteNum() - 1];
                break;
            default:
                sprite = sprites.down1;
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return npc.getValues().getWorldPosition().getX() + ScreenSetting.TILE_SIZE > Game.player.values.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            npc.getValues().getWorldPosition().getX() - ScreenSetting.TILE_SIZE < Game.player.values.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            npc.getValues().getWorldPosition().getY() + ScreenSetting.TILE_SIZE > Game.player.values.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            npc.getValues().getWorldPosition().getY() - ScreenSetting.TILE_SIZE < Game.player.values.getWorldPosition().getX() + Game.player.screenPosition.getY();
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        this.screenX = npc.getValues().getWorldPosition().getX() - Game.player.values.getWorldPosition().getX() + Game.player.screenPosition.getX();
        this.screenY = npc.getValues().getWorldPosition().getY() - Game.player.values.getWorldPosition().getY() + Game.player.screenPosition.getY();
    }
}
