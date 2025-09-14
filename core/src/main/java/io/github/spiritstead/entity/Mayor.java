package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;

import java.util.Random;

public class Mayor extends Entity {
    GamePanel gp;
    public int actionLockCounter = 0;

    public Mayor(GamePanel gp) {
        super(gp);
        this.gp = gp;
        direction = Direction.LEFT;
        speed = 1;
        loadPlayerTextures();

    }

    private void loadPlayerTextures() {
        up1 = new Sprite(new Texture("player/up1.png"));
        up2 = new Sprite(new Texture("player/up2.png"));
        down1 = new Sprite(new Texture("player/down1.png"));
        down2 = new Sprite(new Texture("player/down2.png"));
        left1 = new Sprite(new Texture("player/left1.png"));
        left2 = new Sprite(new Texture("player/left2.png"));
        right1 = new Sprite(new Texture("player/right1.png"));
        right2 = new Sprite(new Texture("player/right2.png"));
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100);

            if (i < 50) {
                direction = Direction.UP;
            }
            if (i > 50) {
                direction = Direction.UP;
            }
            actionLockCounter = 0;
        }

    }

    public void update() {
        setAction();
        checkPlayerCollision();
        move();

    }

    private void move() {
        if (!collisionOn) {
            switch (direction) {
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

    private void checkPlayerCollision() {
        collisionOn = false;
        gp.cChecker.checkEntityIsCollidingWithCollidableTile(this);
        gp.cChecker.checkEntityIsCollidingWithObject(this, false);
        gp.cChecker.checkEntityIsCollidingWithPlayer(this);

    }

    public void draw(SpriteBatch batch) {

        //Calculate where on the screen to draw the tile relative to the player
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //only draw the tile if it is within the screen bounds plus one tile size to blend
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            Sprite currentSprite = null;
            switch (direction) {
                case UP:
                    if (spriteNum == 1) {
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
        }

    }

}
