package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;

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
        int screenX = worldX - gp.playScreen.player.worldX + gp.playScreen.player.screenX;
        int screenY = worldY - gp.playScreen.player.worldY + gp.playScreen.player.screenY;

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if (worldX + gp.tileSize > gp.playScreen.player.worldX - gp.playScreen.player.screenX &&
            worldX - gp.tileSize < gp.playScreen.player.worldX + gp.playScreen.player.screenX &&
            worldY + gp.tileSize > gp.playScreen.player.worldY - gp.playScreen.player.screenY &&
            worldY - gp.tileSize < gp.playScreen.player.worldY + gp.playScreen.player.screenY) {

            batch.draw(image, screenX, screenY, gp.tileSize, gp.tileSize);
        }
    }
}
