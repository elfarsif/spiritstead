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
        Game.aSetter.gameObjects.add(new Axe(
                new Sprite(new Texture("objects/axe.png")),
                new SolidArea(0, 0, 48, 48),
                new WorldPosition(25 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE))
        );
        /*Game.aSetter.obj.add(new Axe(
                new Sprite(new Texture("objects/axe.png")),
                new SolidArea(0, 0, 48, 48),
                new WorldPosition(25 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE))
        );*/
    }

    @Override
    public void draw() {

    }
}
