package io.github.spiritstead.entity.npc.mayor;

import io.github.spiritstead.collision.*;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.npc.NPCDrawer;
import io.github.spiritstead.entity.npc.NPCMover;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.Values;
import io.github.spiritstead.entity.Sprites;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;
import java.util.Random;

public class Mayor implements Collidable, NPC {
    Sprites sprites;
    public int spriteNum = 1;
    private SolidArea solidArea;
    public boolean collisionOn = false;
    private Values values;
    GamePanel gp;
    MayorDialogue mayorDialogue;
    private NPCMover NPCMover;
    private NPCDrawer NPCDrawer;
    private FrameGate frameGate;
    private ArrayList<Collision> collisionTypes = new ArrayList<>();

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.sprites = new Sprites();
        this.NPCMover = new NPCMover(this);
        this.NPCDrawer = new NPCDrawer(gp, this, sprites);
        this.frameGate = new FrameGate(120);
        this.collisionTypes.add(new TileCollision(Game.tileM, this));
        this.collisionTypes.add(new ObjectCollision(this));
        this.collisionTypes.add(new PlayerCollision(this.gp, this));
        this.values = new Values();

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
        NPCMover.move();

    }

    public void speak() {
        Game.ui.dialogueUI.text.currentDialogue = mayorDialogue.getCurrentDialogue();
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
