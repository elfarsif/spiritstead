package io.github.spiritstead.dialogue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.object.Axe;

public class SpawnAxe implements DialogueEvent {

    @Override
    public void handle() {
        System.out.println("spawn axe on map");
        Game.resources.gameObjects.add(Game.resources.axe2);
    }

    @Override
    public void draw() {

    }
}
