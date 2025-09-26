package io.github.spiritstead.entity.npc.mayor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.TileColliadable;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.npc.NPCDrawer;
import io.github.spiritstead.entity.npc.NPCMover;
import io.github.spiritstead.entity.npc.NPCSpriteLoader;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

public class Mayor implements TileColliadable, ObjectColliadable, NPC {
    private WorldPosition worldPosition = new WorldPosition();
    public int speed;
    public Sprite up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;
    public int spriteNum = 1;
    private SolidArea solidArea;
    public boolean collisionOn = false;
    public EnumMap<Direction, Sprite[]> frames = new EnumMap<>(Direction.class);
    GamePanel gp;
    MayorDialogue mayorDialogue;
    private NPCSpriteLoader NPCSpriteLoader;
    private NPCMover NPCMover;
    private NPCDrawer NPCDrawer;
    private FrameGate frameGate;
    private ArrayList<Collision> collisionTypes = new ArrayList<>();

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.NPCSpriteLoader = new NPCSpriteLoader(this);
        this.NPCMover = new NPCMover(this);
        this.NPCDrawer = new NPCDrawer(gp, this);
        this.frameGate = new FrameGate(120);
        this.collisionTypes.add(new TileCollision(this.gp.tileM, this));
        this.collisionTypes.add(new ObjectCollision(this.gp, this));
        this.collisionTypes.add(new PlayerCollision(this.gp, this));

        solidArea = new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

        NPCSpriteLoader.load();
        direction = Direction.LEFT;
        speed = 1;
        mayorDialogue = new MayorDialogue(gp.script.mayorDialogue);

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
//        setAction();
        checkCollisions();
        NPCMover.move();

    }

    public void speak() {
        gp.ui.dialogueUI.text.currentDialogue = mayorDialogue.getCurrentDialogue();
    }

    private void checkCollisions() {
        this.collisionOn = false;
        for (Collision collision : this.collisionTypes) {
            collision.check();
        }
    }

    public void draw() {
        NPCDrawer.draw();

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
    public void setSolidArea(SolidArea solidArea) {
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
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

    @Override
    public void setCollisonOn(boolean collisonOn) {
        this.collisionOn = collisonOn;
    }

}
