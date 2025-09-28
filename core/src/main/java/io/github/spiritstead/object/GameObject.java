package io.github.spiritstead.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;

public class GameObject implements Collidable {
    public Sprite image;
    public String name;
    public boolean collision = false;
    private WorldPosition worldPosition = new WorldPosition();
    SolidArea solidArea = new SolidArea(0, 0, 48, 48);

    public void draw(SpriteBatch batch, GamePanel gp) {
        //Calculate where on the screen to draw the tile relative to the player
        int screenX = worldPosition.getX() - Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX();
        int screenY = worldPosition.getY() - Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY();

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if (worldPosition.getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            worldPosition.getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            worldPosition.getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            worldPosition.getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY()) {

            batch.draw(image, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        }
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {

    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Direction getDirection() {
        return null;
    }
}
