package io.github.spiritstead.entity;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    private GamePanel gp;
    private KeyHandler keyH;
    private SpriteBatch batch;
    private Sprite solidAreaOutlineSprite;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;

        generateSolidAreaOutline();
        setDefaultPlayerValues();
        loadPlayerTextures();
    }

    private void setDefaultPlayerValues(){
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
        speed = 4;
        direction = Direction.DOWN;
    }

    private void loadPlayerTextures(){
        up1 = new Sprite(new Texture("player/up1.png"));
        up2 = new Sprite(new Texture("player/up2.png"));
        down1 = new Sprite(new Texture("player/down1.png"));
        down2 = new Sprite(new Texture("player/down2.png"));
        left1 = new Sprite(new Texture("player/left1.png"));
        left2 = new Sprite(new Texture("player/left2.png"));
        right1 = new Sprite(new Texture("player/right1.png"));
        right2 = new Sprite(new Texture("player/right2.png"));
    }

    public void update(){
        if (keyH.upPressed|| keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            assignKeyPressToDirection();
            checkPlayerCollision();
            move();
            handleSpriteAnimation();
        }

    }

    private void assignKeyPressToDirection() {
        if (keyH.upPressed){
            direction = Direction.UP;
        }
        else if (keyH.downPressed){
            direction = Direction.DOWN;
        }
        else if (keyH.leftPressed) {
            direction = Direction.LEFT;
        }
        else if (keyH.rightPressed) {
            direction = Direction.RIGHT;
        }

    }

    private void handleSpriteAnimation() {
        spriteCounter++;
        if (spriteCounter>15){
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter =0;
        }
    }

    private void checkPlayerCollision() {
        collisionOn = false;
        gp.cChecker.checkEntityIsCollidingWithCollidableTile(this);

    }

    private void move(){
        if (!collisionOn){
            switch (direction){
                case UP:
                    worldY += speed;
                    break;
                case DOWN:
                    worldY -= speed;
                    break;
                case LEFT:
                    worldX -= speed;
                    break;
                case RIGHT:
                    worldX += speed;
                    break;
            }
        }

    }

    private void generateSolidAreaOutline(){
        Pixmap solidAreaPixmap = new Pixmap(solidArea.width, solidArea.height, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.solidAreaOutlineSprite = solidAreaSprite;
    }

    public void draw(SpriteBatch batch){
        this.batch= batch;
        Sprite currentSprite = null;
        switch (direction){
            case UP:
                if (spriteNum ==1) {
                    currentSprite = up1;
                } else if (spriteNum == 2) {
                    currentSprite = up2;
                }
                break;
            case DOWN:
                if (spriteNum == 1) {
                    currentSprite = down1;
                } else if (spriteNum == 2) {
                    currentSprite = down2;
                }
                break;
            case LEFT:
                if (spriteNum == 1) {
                    currentSprite = left1;
                } else if (spriteNum == 2) {
                    currentSprite = left2;
                }
                break;
            case RIGHT:
                if (spriteNum == 1) {
                    currentSprite = right1;
                } else if (spriteNum == 2) {
                    currentSprite = right2;
                }
                break;
            default:
                currentSprite = down1;
        }
        batch.draw(currentSprite, screenX, screenY, gp.tileSize, gp.tileSize);
        batch.draw(solidAreaOutlineSprite,screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }



}
