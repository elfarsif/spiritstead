package io.github.spiritstead.entity.mayor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;
import java.util.Random;

public class Mayor extends Entity {
    GamePanel gp;
    MayorDialogue dialogue;
    private EntitySpriteLoader entitySpriteLoader;
    private EntityMover entityMover;
    private EntityDrawer entityDrawer;
    private FrameGate frameGate;
    private ArrayList<Collision> collisionTypes = new ArrayList<>();

    public Mayor(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.entitySpriteLoader = new EntitySpriteLoader(this);
        this.entityMover = new EntityMover(this);
        this.entityDrawer = new EntityDrawer(gp, this);
        this.frameGate = new FrameGate(120);
        this.collisionTypes.add(new TileCollision(this.gp, this));
        this.collisionTypes.add(new ObjectCollision(this.gp, this));
        this.collisionTypes.add(new PlayerCollision(this.gp, this));

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
        super.collisionOn = false;
        for (Collision collision : this.collisionTypes) {
            collision.check();
        }
    }

    public void draw() {
        entityDrawer.draw();

    }

}
