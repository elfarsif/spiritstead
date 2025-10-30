package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.tools.FrameGate;

import java.util.List;

/**
 * This class is used to animate a list of sprites
 */
public final class Animation {
    private final List<Sprite> sprites;
    private final FrameGate frameGate;
    private final Runnable onCycleComplete;
    private int spriteNum = 0;

    private Animation(FrameGate frameGate, List<Sprite> sprites, Runnable onCycleComplete) {
        this.sprites = sprites;
        this.frameGate = frameGate;
        this.onCycleComplete = onCycleComplete;
    }

    public static Animation looping(FrameGate gate, List<Sprite> sprites) {
        return new Animation(gate, sprites, () -> {
        });
    }

    public static Animation singleAction(FrameGate gate, State state, List<Sprite> sprites) {
        Runnable onCycleComplete = new Runnable() {
            @Override
            public void run() {
                Game.keyH.inputGate.open();
                state.setCurrent(state.getDefault());
            }
        };
        return new Animation(gate, sprites, onCycleComplete);
    }

    public boolean update() {
        if (frameGate.tick()) {
            spriteNum++;
            if (spriteNum == sprites.size()) {
                spriteNum = 0;
                onCycleComplete.run();
            }
            this.frameGate.reset();
        }
        return true;
    }

    public Sprite getCurrentSprite() { return this.sprites.get(spriteNum); }

}
