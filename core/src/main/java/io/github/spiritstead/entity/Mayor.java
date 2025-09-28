package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.dialogue.Dialogue;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mayor implements NPC {
    private Sprite sprite;
    Sprites sprites;
    private Mover mover;
    public int spriteNum = 1;
    private SolidArea solidArea;
    public boolean collisionOn = false;
    private int speed;
    private Direction direction;
    GamePanel gp;
    private FrameGate frameGate;
    private TileCollisionType tileCollision;
    private Collision collision;
    public WorldPosition worldPosition = new WorldPosition();
    public Map<Integer, String> allDialogue = new HashMap<>();
    private int index = 0;
    String currentDialogue;
    private int screenX, screenY;
    public Dialogue dialogue;

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.mover = new Mover(this);
        this.sprites = new Sprites();
        this.frameGate = new FrameGate(120);
        this.tileCollision = new TileCollisionType(Game.tileM, this);
        this.collision = new Collision();

        this.speed = 1;
        this.solidArea = new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        this.sprites.load();
        this.direction = Direction.LEFT;
        this.allDialogue = Game.script.mayorDialogue;
        this.dialogue = new Dialogue();
    }

    public void setAction() {
        if (this.frameGate.tick()) {
            this.direction = Direction.UP;
            this.frameGate.reset();
        }
    }

    public void update() {
        setAction();
        checkCollisions();
        this.mover.move();
    }

    public void interact() {
        if (dialogue.node != null) {
            Game.ui.dialogueUI.text.currentDialogue = this.dialogue.node.dialogue;
            this.dialogue.node = this.dialogue.node.left;
        } else {
            System.out.println("play game");
            Game.screens.setScreen(Game.screens.gameScreen);
            Game.ui.uiScreen = Game.ui.gameScreenUI;
        }
    }

    public String getCurrentDialogue() {
        this.currentDialogue = allDialogue.get(this.index);
        this.index++;
        return this.currentDialogue;
    }

    private void checkCollisions() {
        this.collisionOn = false;
        tileCollision.check();
        checkPlayerCollision();
        checkObjectCollision();

    }

    private void checkPlayerCollision() {
        if (collision.check(this, Game.player)) {
            this.collisionOn = true;
        }
    }

    private void checkObjectCollision() {
        for (int i = 0; i < Game.aSetter.objects.length; i++) {
            if (Game.aSetter.objects[i] != null && collision.check(this, Game.aSetter.objects[i])) {
                this.collision.check(this, Game.aSetter.objects[i]);
            }
        }
    }

    public void draw() {
        initialiazeScreenPositionRelativeToPlayer();
        if (entityIsWithinScreenBounds()) {
            updateSprite();
            Game.batch.draw(sprite, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

        }
    }

    private void updateSprite() {
        sprite = null;
        switch (direction) {
            case UP:
                sprite = sprites.frames.get(Direction.UP)[spriteNum - 1];
                break;
            case DOWN:
                sprite = sprites.frames.get(Direction.DOWN)[spriteNum - 1];
                break;
            case LEFT:
                sprite = sprites.frames.get(Direction.LEFT)[spriteNum - 1];
                break;
            case RIGHT:
                sprite = sprites.frames.get(Direction.RIGHT)[spriteNum - 1];
                break;
            default:
                sprite = sprites.down1;
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return getWorldPosition().getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
            getWorldPosition().getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
            getWorldPosition().getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
            getWorldPosition().getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getY();
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        this.screenX = getWorldPosition().getX() - Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX();
        this.screenY = getWorldPosition().getY() - Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY();
    }

    @Override
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

}
