package io.github.spiritstead.dialogue;

import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.object.Axe;

public class SpawnAxe implements DialogueEvent {

    @Override
    public void handle() {
        System.out.println("spawn axe on map");
        Game.aSetter.obj.add(new Axe(25 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));
    }

    @Override
    public void draw() {

    }
}
