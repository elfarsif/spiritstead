package io.github.spiritstead.screens;

import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.GameSystem;

public class ScreenManager {
    private GamePanel gp;
    public Screen screen;
    public TitleScreen titleScreen;
    public CutsceneScreen cutsceneScreen;
    public GameScreen gameScreen;

    public ScreenManager(GamePanel gp, GameSystem system, Player player) {
        this.gp = gp;
        titleScreen = new TitleScreen(gp);
        cutsceneScreen = new CutsceneScreen(gp);
        gameScreen = new GameScreen(gp, system, player);

        screen = titleScreen;
    }

    public void drawScreen() {
        screen.draw();
    }

    public void dispose() {
        titleScreen.dispose();
        gameScreen.dispose();
    }

}
