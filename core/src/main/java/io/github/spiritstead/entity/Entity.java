package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.player.PlayerObjectInteractor;
import io.github.spiritstead.main.GamePanel;

import java.awt.*;
import java.util.Random;

/*
This class represents an entity in the game. It is a parent class for players, monsters, npcs
 */
public class Entity {
    //world 0,0 is bottom left
    public int worldX, worldY;
    public int speed;

    public Sprite up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public Rectangle solidArea;
    //allows to change solid area for collision detection and store original values to restore area
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;

    String dialogues[] = new String[20];

    public Entity(GamePanel gp) {
        solidArea = new Rectangle(0, 0, gp.sSetting.tileSize, gp.sSetting.tileSize);

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
