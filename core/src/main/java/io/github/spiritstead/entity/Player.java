package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler keyH;
    private SpriteBatch batch;
    private Sprite solidAreaOutlineSprite;
    public int hasKey = 0;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.sSetting.screenWidth / 2 - gp.sSetting.tileSize / 2;
        screenY = gp.sSetting.screenHeight / 2 - gp.sSetting.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 5 * gp.sSetting.scale;
        solidArea.y = 0;
        //record default values to change solid area
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 6 * gp.sSetting.scale;
        solidArea.height = 6 * gp.sSetting.scale;

        generateSolidAreaOutline();
        setDefaultPlayerValues();
        loadPlayerTextures();
    }

    private void setDefaultPlayerValues() {
        worldX = gp.sSetting.tileSize * 28;
        worldY = gp.sSetting.tileSize * 13;
        speed = 4;
        direction = direction.DOWN;
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

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            assignKeyPressToDirection();
            checkTileCollision();
            checkNPCCollision();
            checkEventCollision();
            move();
            handleSpriteAnimation();
        }

    }

    private void checkNPCCollision() {
        int npcIndex = gp.system.cChecker.checkPlayerIsCollidingWithEntity(this, gp.system.aSetter.npcs);
        interactNPC(npcIndex);
    }

    private void interactNPC(int npcIndex) {
        if (npcIndex != 9999) {
            gp.system.ui.uiScreen = gp.system.ui.dialogueUI;
        }
    }

    private void checkEventCollision() {
        gp.system.eHandler.checkEvent();
    }

    private void assignKeyPressToDirection() {
        if (keyH.upPressed) {
            direction = direction.UP;
        } else if (keyH.downPressed) {
            direction = direction.DOWN;
        } else if (keyH.leftPressed) {
            direction = direction.LEFT;
        } else if (keyH.rightPressed) {
            direction = direction.RIGHT;
        }

    }

    private void handleSpriteAnimation() {
        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    private void checkTileCollision() {
        collisionOn = false;
        gp.system.cChecker.checkEntityIsCollidingWithCollidableTile(this);
        int objIndex = gp.system.cChecker.checkEntityIsCollidingWithObject(this, true);
        pickUpObject(objIndex);

    }

    private void pickUpObject(int index) {
        if (index != 9999) {
            String objName = gp.system.aSetter.objects[index].name;
            switch (objName) {
                case "Key":
                    hasKey++;
                    gp.system.aSetter.objects[index] = null;
                    gp.system.audioPlayer.playSE(1);
                    gp.system.ui.gameScreenUI.showMessage("You got a key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.system.aSetter.objects[index] = null;
                        hasKey--;
                        gp.system.ui.gameScreenUI.showMessage("You opened the door!");
                    } else {
                        gp.system.ui.gameScreenUI.showMessage("You need a key");
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.system.aSetter.objects[index] = null;
                    gp.system.audioPlayer.playSE(2);
                    gp.system.ui.gameScreenUI.showMessage("YOU ARE FAST");
                    break;
                case "Chest":
                    gp.system.ui.gameScreenUI.gameFinished = true;
                    gp.system.audioPlayer.playSE(2);
                    break;

            }
        }
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

    private void generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(gp.sSetting.tileSize, gp.sSetting.tileSize, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(solidArea.x, gp.sSetting.tileSize - solidArea.height, solidArea.width, solidArea.height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.solidAreaOutlineSprite = solidAreaSprite;
    }

    public void draw(SpriteBatch batch) {
        this.batch = batch;
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
        batch.draw(currentSprite, screenX, screenY, gp.sSetting.tileSize, gp.sSetting.tileSize);
        batch.draw(solidAreaOutlineSprite, screenX, screenY);
    }

}
