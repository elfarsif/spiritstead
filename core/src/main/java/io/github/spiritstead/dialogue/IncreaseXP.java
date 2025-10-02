package io.github.spiritstead.dialogue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;

public class IncreaseXP implements DialogueEvent {
    private Texture texture;

    public IncreaseXP() {
        this.generateTexture();
    }

    private void generateTexture() {
        Pixmap pixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void handle() {
        System.out.println("player xp +1");
        Game.player.xp++;
        System.out.println("player xp is " + Game.player.xp);
    }

    @Override
    public void draw() {
        Game.batch.draw(texture, ScreenSetting.TILE_SIZE, ScreenSetting.SCREEN_HEIGHT / 2);
    }
}
