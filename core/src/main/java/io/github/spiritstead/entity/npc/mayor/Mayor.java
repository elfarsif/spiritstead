package io.github.spiritstead.entity.npc.mayor;

import io.github.spiritstead.collision.*;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.npc.Action;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.npc.NPCDrawer;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.Values;
import io.github.spiritstead.entity.Sprites;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.object.GameObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Mayor implements Collidable, Moveable, NPC {
    Sprites sprites;
    private Mover mover;
    public int spriteNum = 1;
    private SolidArea solidArea;
    public boolean collisionOn = false;
    private Values values;
    GamePanel gp;
    MayorDialogue mayorDialogue;
    private NPCDrawer NPCDrawer;
    private FrameGate frameGate;
    private ArrayList<Collision> collisionTypes = new ArrayList<>();
    private Collision2 collision;

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.mover = new Mover(this);
        this.sprites = new Sprites();
        this.NPCDrawer = new NPCDrawer(gp, this, sprites);
        this.frameGate = new FrameGate(120);
        this.collisionTypes.add(new TileCollision(Game.tileM, this));
        this.collisionTypes.add(new ObjectCollision(this));
        this.values = new Values();
        this.collision = new Collision2();

        solidArea = new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        sprites.load();
        this.values.setSpeed(1);
        this.values.setDirection(Direction.LEFT);
        mayorDialogue = new MayorDialogue(Game.script.mayorDialogue);
    }

    public void setAction() {
        if (frameGate.tick()) {
            Random random = new Random();
            int i = random.nextInt(100);

            if (i < 50) {
                this.values.direction = Direction.UP;
            }
            if (i > 50) {
                this.values.direction = Direction.UP;
            }
            frameGate.reset();
        }

    }

    public void update() {
        setAction();
        checkCollisions();
        mover.move();
    }

    public void speak() {
        Game.ui.dialogueUI.text.currentDialogue = mayorDialogue.getCurrentDialogue();
    }

    private void checkCollisions() {
        this.collisionOn = false;
        for (Collision collision : this.collisionTypes) {
            collision.check();
        }
        if (collision.check(this, Game.player)) {
            this.collisionOn = true;
        }
    }

    public void draw() {
        NPCDrawer.draw();
    }

    @Override
    public Values getValues() {
        return this.values;
    }

    @Override
    public int getSpriteNum() {
        return this.spriteNum;
    }

    @Override
    public void setDirection(Direction direction) {
        this.getValues().direction = direction;
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
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

}
