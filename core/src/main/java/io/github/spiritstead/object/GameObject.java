package io.github.spiritstead.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;

public class GameObject {
    public Sprite image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(SpriteBatch batch, GamePanel gp) {
        //Calculate where on the screen to draw the tile relative to the player
        int screenX = worldX - Game.player.values.getWorldPosition().getX() + Game.player.screenPosition.getX();
        int screenY = worldY - Game.player.values.getWorldPosition().getY() + Game.player.screenPosition.getY();

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if (worldX + ScreenSetting.TILE_SIZE > Game.player.values.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            worldX - ScreenSetting.TILE_SIZE < Game.player.values.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            worldY + ScreenSetting.TILE_SIZE > Game.player.values.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            worldY - ScreenSetting.TILE_SIZE < Game.player.values.getWorldPosition().getY() + Game.player.screenPosition.getY()) {

            batch.draw(image, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        }
    }
}
