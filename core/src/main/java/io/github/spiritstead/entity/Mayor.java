package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
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
    private ArrayList<CollisionType> collisionTypeTypes = new ArrayList<>();
    private Collision collision;
    public WorldPosition worldPosition = new WorldPosition();
    public Map<Integer, String> allDialogue = new HashMap<>();
    private int index = 0;
    String currentDialogue;
    private int screenX, screenY;

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.mover = new Mover(this);
        this.sprites = new Sprites();
        this.frameGate = new FrameGate(120);
        this.collisionTypeTypes.add(new TileCollisionType(Game.tileM, this));
        this.collisionTypeTypes.add(new ObjectCollisionType(this));
        this.collision = new Collision();

        speed = 1;
        solidArea = new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        sprites.load();
        this.direction = Direction.LEFT;
        this.allDialogue = Game.script.mayorDialogue;
    }

    public void setAction() {
        if (frameGate.tick()) {
            this.direction = Direction.UP;
            frameGate.reset();
        }

    }

    public void update() {
        setAction();
        checkCollisions();
        mover.move();
    }

    public void speak() {
        Game.ui.dialogueUI.text.currentDialogue = getCurrentDialogue();
    }

    public String getCurrentDialogue() {
        this.currentDialogue = allDialogue.get(this.index);
        this.index++;
        return this.currentDialogue;
    }

    private void checkCollisions() {
        this.collisionOn = false;
        for (CollisionType collisionType : this.collisionTypeTypes) {
            collisionType.check();
        }
        if (collision.check(this, Game.player)) {
            this.collisionOn = true;
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
