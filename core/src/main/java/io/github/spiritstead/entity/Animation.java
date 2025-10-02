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

    public Animation(FrameGate frameGate, ArrayList<Sprite> sprites) {
        this.frameGate = frameGate;
        this.sprites = sprites;
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
}
