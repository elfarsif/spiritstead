package io.github.spiritstead.entity.mayor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.player.TileColliadable;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

public class Mayor implements Updatable, Drawable, TileColliadable,
    ObjectColliadable, PlayerInteractable {
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

    //
    GamePanel gp;
    MayorDialogue dialogue;
    private EntitySpriteLoader entitySpriteLoader;
    private EntityMover entityMover;
    private EntityDrawer entityDrawer;
    private FrameGate frameGate;
    private ArrayList<Collision> collisionTypes = new ArrayList<>();

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.entitySpriteLoader = new EntitySpriteLoader(this);
        this.entityMover = new EntityMover(this);
        this.entityDrawer = new EntityDrawer(gp, this);
        this.frameGate = new FrameGate(120);
        this.collisionTypes.add(new TileCollision(this.gp, this));
        this.collisionTypes.add(new ObjectCollision(this.gp, this));
        this.collisionTypes.add(new PlayerCollision(this.gp, this));

        solidArea = new Rectangle(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

        entitySpriteLoader.load();
        direction = Direction.LEFT;
        speed = 1;
        dialogue = new MayorDialogue();

        frames.put(Direction.UP, new Sprite[]{up1, up2});
        frames.put(Direction.DOWN, new Sprite[]{down1, down2});
        frames.put(Direction.LEFT, new Sprite[]{left1, left2});
        frames.put(Direction.RIGHT, new Sprite[]{right1, right2});

    }

    public void setAction() {
        if (frameGate.tick()) {
            Random random = new Random();
            int i = random.nextInt(100);

            if (i < 50) {
                direction = Direction.UP;
            }
            if (i > 50) {
                direction = Direction.UP;
            }
            frameGate.reset();
        }

    }

    public void update() {
        setAction();
        checkCollisions();
        entityMover.move();

    }

    public void speak() {
        gp.system.ui.dialogueUI.text.currentDialogue = dialogue.array[dialogue.index];
        dialogue.index++;
    }

    private void checkCollisions() {
        this.collisionOn = false;
        for (Collision collision : this.collisionTypes) {
            collision.check();
        }
    }

    public void draw() {
        entityDrawer.draw();

    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getSpriteNum() {
        return this.spriteNum;
    }

    @Override
    public Sprite getDown1() {
        return this.down1;
    }

    @Override
    public void setUp1(Sprite up1) {
        this.up1 = up1;
    }

    @Override
    public void setUp2(Sprite up2) {
        this.up2 = up2;
    }

    @Override
    public void setDown1(Sprite down1) {
        this.down1 = down1;
    }

    @Override
    public void setDown2(Sprite down2) {
        this.down2 = down2;
    }

    @Override
    public void setLeft1(Sprite left1) {
        this.left1 = left1;
    }

    @Override
    public void setLeft2(Sprite left2) {
        this.left2 = left2;
    }

    @Override
    public void setRight1(Sprite right1) {
        this.right1 = right1;
    }

    @Override
    public void setRight2(Sprite right2) {
        this.right2 = right2;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public EnumMap<Direction, Sprite[]> getFrames() {
        return this.frames;
    }

    @Override
    public int getSolidAreadDefaultX() {
        return this.solidAreaDefaultX;
    }

    @Override
    public int getSolidAreadDefaultY() {
        return this.solidAreaDefaultY;
    }

    @Override
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public int getWorldX() {
        return this.worldX;
    }

    @Override
    public int getWorldY() {
        return this.worldY;
    }

    @Override
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    @Override
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    @Override
    public Rectangle getSolidArea() {
        return this.solidArea;
    }

    @Override
    public void setCollisonOn(boolean collisonOn) {
        this.collisionOn = collisonOn;
    }

}
