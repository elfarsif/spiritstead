package io.github.spiritstead.entity;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

public class Player extends Entity{
    private GamePanel gp;
    private KeyHandler keyH;
    private SpriteBatch batch;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultPlayerValues();
        loadPlayerTextures();
    }

    private void setDefaultPlayerValues(){
        x = gp.tileSize * 3;
        y = gp.tileSize * 3;
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
            move();
        }

    }

    private void move() {
        if (keyH.upPressed){
            direction = Direction.UP;
            y += speed;
        }
        else if (keyH.downPressed){
            direction = Direction.DOWN;
            y -= speed;
        }
        else if (keyH.leftPressed) {
            direction = Direction.LEFT;
            x -= speed;
        }
        else if (keyH.rightPressed) {
            direction = Direction.RIGHT;
            x += speed;
        }

        //handle sprite animation
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

    public void draw(SpriteBatch batch){
        this.batch= batch;
        Sprite currentTexture = null;
        switch (direction){
            case UP:
                if (spriteNum ==1) {
                    currentTexture = up1;
                } else if (spriteNum == 2) {
                    currentTexture = up2;
                }
                break;
            case DOWN:
                if (spriteNum == 1) {
                    currentTexture = down1;
                } else if (spriteNum == 2) {
                    currentTexture = down2;
                }
                break;
            case LEFT:
                if (spriteNum == 1) {
                    currentTexture = left1;
                } else if (spriteNum == 2) {
                    currentTexture = left2;
                }
                break;
            case RIGHT:
                if (spriteNum == 1) {
                    currentTexture = right1;
                } else if (spriteNum == 2) {
                    currentTexture = right2;
                }
                break;
            default:
                currentTexture = down1;
        }
        batch.draw(currentTexture, x, y, gp.tileSize, gp.tileSize);
    }



}
