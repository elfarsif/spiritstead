package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;

public class Animation {
    private FrameGate frameGate;
    private ArrayList<Sprite> sprites;
    private Sprite sprite;
    private int spriteNum = 0;
    private int axeAnimationCount = 0;
    private StateHandler stateHandler;

    public Animation(FrameGate frameGate, ArrayList<Sprite> sprites) {
        this.frameGate = frameGate;
        this.sprites = sprites;
    }
    public Animation(StateHandler stateHandler, ArrayList<Sprite> sprites) {
        this.sprites = sprites;
        this.stateHandler = stateHandler;
    }
    public void draw(int screenX, int screenY, int width, int height) {
        this.updateSNum();
        this.updateSprite();
        Game.batch.draw(sprite, screenX, screenY, width, height);
    }

    private void updateSNum() {
        if (frameGate.tick()) {
            spriteNum++;
            if (spriteNum == sprites.size()) {
                spriteNum = 0;
            }
            this.frameGate.reset();
        }
    }

    private void updateSprite() {
        this.sprite = sprites.get(spriteNum);
    }

    public Sprite getSprite() {
        return sprite;
    }
    public void drawSingleLoop(int screenX, int screenY, int width, int height) {
        Game.keyH.inputGate.close();
        if (axeAnimationCount >= 0 && axeAnimationCount < 30) {
            sprite = sprites.get(0);
        } else if (axeAnimationCount >= 30 && this.axeAnimationCount < 60) {
            sprite = sprites.get(1);
        } else if (axeAnimationCount > 60) {
            sprite = sprites.get(0);
            axeAnimationCount = 0;
            Game.keyH.inputGate.open();
            stateHandler.setCurrentState(PlayerState.NORMAL);
        }
        axeAnimationCount++;
        Game.batch.draw(sprite, screenX, screenY, width, height);
    }
}
