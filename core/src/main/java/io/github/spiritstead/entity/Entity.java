package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;
import java.util.EnumMap;

/**
 * This class represents an entity in the game. It is a parent class for players, monsters, npcs
 **/
public class Entity implements Updatable, Drawable {
    public int worldX, worldY;
    public int speed;

    public Sprite up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int spriteNum = 1;

    public Rectangle solidArea;
    //allows to change solid area for collision detection and store original values to restore area
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;

    public EnumMap<Direction, Sprite[]> frames = new EnumMap<>(Direction.class);

    public int screenX;
    public int screenY;

    public Entity(GamePanel gp) {
        solidArea = new Rectangle(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

    }

    public void speak() {

    }

    public void setAction() {

    }

    public void update() {

    }

    public void draw() {

    }
}
