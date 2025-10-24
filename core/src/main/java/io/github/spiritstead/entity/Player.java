package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.Collision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.Axe;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.object.Tree;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;
import java.util.Arrays;

public final class Player implements Collidable, Moveable, Updatable {
    private final Sprites sprites;
    private final StateHandler stateHandler;
    private final Mover mover;
    private final FrameGate frameGate;
    private final Collision collision;
    private final TileCollision tileCollision;
    private final WorldPosition worldPosition;
    private final Animation axeAnimation;
    private final SolidArea solidArea;
    private final Direction.Holder direction;
    private final Sprite solidAreaOutline;
    private final ScreenPosition screenPosition;
    private final Inventory inventory;

    private Sprite currentSprite;
    private int spriteNum = 1;
    private boolean collisionOn = false;
    private int xp = 3;
    private int hasKey = 0;
    private int speed;

    public Player(Sprites sprites, ScreenPosition screenPosition, SolidArea solidArea, WorldPosition worldPosition,
                  int initialSpeed, Inventory inventory, Direction.Holder direction) {
        this.sprites = sprites;
        this.screenPosition = screenPosition;
        this.solidArea = solidArea;
        this.speed = initialSpeed;
        this.worldPosition = worldPosition;
        this.inventory = inventory;
        this.direction = direction;
        this.mover = new Mover(this);
        this.frameGate = new FrameGate(15);
        this.collision = new Collision();
        this.tileCollision = new TileCollision(Game.tileM, this);
        this.stateHandler = new StateHandler(PlayerState.NORMAL);
        this.axeAnimation = Animation.singleAction(new FrameGate(30), this.stateHandler, new ArrayList<>(Arrays.asList(
                new Sprite(new Texture("player/right1.png")),
                new Sprite(new Texture("player/right2.png"))
        )));
        this.solidAreaOutline = this.generateSolidAreaOutline();

    }

    private void interactObject(GameObject gameObject) {
        if (gameObject instanceof Tree) {
            if (inventory.getSelectedItem() instanceof Axe) {
                if (Game.keyH.spacePressed) {
                    Game.keyH.spacePressed = false;
                    stateHandler.setCurrentState(PlayerState.AXING);
                    gameObject.interact();
                }
            }
        } else {

            gameObject.interact();
        }

    }

    private void interact(NPC npc) {
        Game.ui.uiScreen = Game.ui.dialogueUI;
        Game.screens.setScreen(Game.screens.dialogueScreen);
        if (inventory.contains(Axe.class)) {
            npc.setDialogueNode(Game.dialogue.axeFoundDialog);
        } else {
            npc.setDialogueNode(Game.dialogue.helpFindAxe);
        }
        npc.interact();
    }
    @Override
    public void update() {
        if (Game.keyH.jPressed) {
            Game.keyH.jPressed = false;
            inventory.setSelectedItemToPrev();
        }
        if (Game.keyH.kPressed) {
            Game.keyH.kPressed = false;
            inventory.setSelectedItemToNext();
        }
        if (stateHandler.isState(PlayerState.AXING)) {
            checkObjectCollision();

        } else if (stateHandler.isState(PlayerState.NORMAL)) {
            if (Game.keyH.upPressed || Game.keyH.downPressed || Game.keyH.leftPressed || Game.keyH.rightPressed) {
                assignKeyPressToDirection(Game.keyH);
                this.collisionOn = this.tileCollision.check();
                Game.eHandler.checkEvent();
                checkNPCCollision();
                checkObjectCollision();
                mover.move();
                updateSprite();
            }
            checkNPCCollision();
            checkObjectCollision();
        }

    }

    private void checkObjectCollision() {
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) != null && collision.check(this, Game.aSetter.obj.get(i))) {
                this.collisionOn = true;
                interactObject(Game.aSetter.obj.get(i));
            }
        }
    }
    private void updateSprite() {
        if (this.frameGate.tick()) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            frameGate.reset();
        }
    }
    private void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            this.direction.set(Direction.UP);
        } else if (keyH.downPressed) {
            this.direction.set(Direction.DOWN);
        } else if (keyH.leftPressed) {
            this.direction.set(Direction.LEFT);
        } else if (keyH.rightPressed) {
            this.direction.set(Direction.RIGHT);
        }
    }
    private void checkNPCCollision() {
        for (int i = 0; i < Game.aSetter.npcs.length - 9; i++) {
            if (collision.check(Game.player, Game.aSetter.npcs[i])) {
                this.collisionOn = true;
                interactNPC(i);
            }
        }

    }
    private void interactNPC(int npcIndex) {
        if (Game.keyH.spacePressed) {
            interact(Game.aSetter.npcs[npcIndex]);
            Game.keyH.spacePressed = false;
        }
    }
    private void checkEventCollision() {
        Game.eHandler.checkEvent();
    }
    public void draw() {
        if (stateHandler.isState(PlayerState.AXING)) {
            Game.keyH.inputGate.close();
            this.axeAnimation.update();
            Game.batch.draw(axeAnimation.getCurrentSprite(), screenPosition.getX(), screenPosition.getY(), ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        } else if (stateHandler.isState(PlayerState.NORMAL)) {
            drawPlayer();
            this.drawSolidArea();
        }

    }
    private void drawPlayer() {
        currentSprite = null;
        currentSprite = sprites.getNextSprite(this.direction.get(), spriteNum);
        Game.batch.draw(currentSprite, screenPosition.getX(), screenPosition.getY(), ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
    }

    private void drawSolidArea() {
        Game.batch.draw(solidAreaOutline, screenPosition.getX(), screenPosition.getY());
    }
    private Sprite generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(
                solidArea.getRect().x,
                ScreenSetting.TILE_SIZE - solidArea.getRect().height,
                solidArea.getRect().width,
                solidArea.getRect().height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        return solidAreaSprite;
    }

    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public Direction getDirection() { return this.direction.get(); }
    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public boolean isCollisionOn() { return this.collisionOn; }
    @Override
    public int getSpeed() { return this.speed; }
    public void increaseSpeedBy(int speed) { this.speed += speed; }
    public void increaseXP(int amount) { this.xp++; }
    public int getXp() { return this.xp; }
    public boolean hasKey() { return this.hasKey > 0; }
    public void removeKey() { this.hasKey--; }
    public void addKey() { this.hasKey++; }
    public void addToInventory(GameObject gameObject) { this.inventory.add(gameObject); }
    public void selectedItem(GameObject gameObject) { this.inventory.setSelectedItem(gameObject); }
    public int inventorySize() { return this.inventory.getItems().size(); }
    public Sprite inventoryItemImage(int i) { return this.inventory.getItems().get(i).getImage(); }
    public boolean isSelectedItem(int i) { return this.inventory.getItems().get(i) == this.inventory.getSelectedItem(); }
    public ScreenPosition getScreenPosition() { return this.screenPosition; }
}
