package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Boots extends GameObject{


    public Boots(){
        name = "Boots";
        image = new Sprite(new Texture("objects/boots.png"));
    }
}
