package io.github.spiritstead.entity.mayor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.GamePanel;

import java.util.Random;

public class Mayor extends Entity {
    GamePanel gp;
    public int actionLockCounter = 0;
    MayorDialogue dialogue;
    private EntitySpriteLoader entitySpriteLoader;
    private EntityMover entityMover;
    private EntityDrawer entityDrawer;

    public Mayor(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.entitySpriteLoader = new EntitySpriteLoader(this);
        this.entityMover = new EntityMover(this);
        this.entityDrawer = new EntityDrawer(gp, this);

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
        entityMover.move();

    }

    public void speak() {
        gp.system.ui.dialogueUI.text.currentDialogue = dialogue.array[dialogue.index];
        dialogue.index++;
    }

    private void checkPlayerCollision() {
        collisionOn = false;
        gp.system.cChecker.checkEntityIsCollidingWithCollidableTile(this);
        gp.system.cChecker.checkEntityIsCollidingWithObject(this, false);
        gp.system.cChecker.checkEntityIsCollidingWithPlayer(this);

    }

    public void draw() {
        entityDrawer.draw();

    }

}
