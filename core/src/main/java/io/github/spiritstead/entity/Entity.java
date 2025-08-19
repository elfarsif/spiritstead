package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/*
This class represents an entity in the game. It is a parent class for players, monsters, npcs
 */
public class Entity {
    public int x,y;
    public int speed;

    Sprite up1, up2,down1, down2, left1, left2, right1, right2;
    Direction direction;

    int spriteCounter = 0;
    int spriteNum = 1;
}
