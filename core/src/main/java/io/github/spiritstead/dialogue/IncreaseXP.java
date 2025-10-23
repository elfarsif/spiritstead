package io.github.spiritstead.dialogue;

import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.Sprite;
import io.github.spiritstead.ui.XPBar;

public class IncreaseXP implements DialogueEvent {
    private XPBar xpBar;

    public IncreaseXP() {
        this.xpBar = new XPBar(
                new Sprite("objects/xpbar.png", ScreenSetting.SCALE),
                new Sprite("objects/xpbarFill.png", ScreenSetting.SCALE)
        );
    }

    @Override
    public void handle() {
        Game.player.increaseXP(1);
        System.out.println("Player XP: " + Game.player.getXp());
    }

    @Override
    public void draw() {
        xpBar.draw(ScreenSetting.TILE_SIZE, ScreenSetting.SCREEN_HEIGHT / 2);
    }
}
