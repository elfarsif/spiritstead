package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Key extends GameObject {

    public Key(){
        name = "Key";
        image = new Sprite(new Texture("objects/key.png"));
    }

}
