package io.github.spiritstead.ui;

import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.Sprite;

public class XPBar {
    private Sprite xpBarFill;
    private Sprite xpBar;

    public XPBar(Sprite xpBar, Sprite xpBarFill) {
        this.xpBar = xpBar;
        this.xpBarFill = xpBarFill;
    }

    public void draw(float screenX, float screenY) {
        xpBar.draw(screenX, screenY);
        float screenXAccumulator = screenX;
        for (int i = 0; i < Game.player.xp; i++) {
            xpBarFill.draw(
                    screenX + (8 * ScreenSetting.SCALE),
                    screenY + (2 * ScreenSetting.SCALE)
            );
            screenX += xpBarFill.getSprite().getWidth();
        }

    }
}
