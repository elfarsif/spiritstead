package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.*;

public interface GameObject extends Collidable {
    public void draw(SpriteBatch batch);

    public void interact();

    Sprite getImage();
}
