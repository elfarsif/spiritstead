package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;

public class ObjectDrawer {
    private WorldPosition worldPosition = new WorldPosition();

    public ObjectDrawer(WorldPosition worldPosition) {
        this.worldPosition = worldPosition;
    }

    public void draw(Sprite image) {
        //Calculate where on the screen to draw the tile relative to the player
        int screenX = worldPosition.getX() - Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX();
        int screenY = worldPosition.getY() - Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY();

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if (worldPosition.getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            worldPosition.getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            worldPosition.getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            worldPosition.getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY()) {

            Game.batch.draw(image, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        }
    }

}
