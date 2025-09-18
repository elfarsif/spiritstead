package io.github.spiritstead.screens;

import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.GameSystem;

public class ScreenManager {
    private GamePanel gp;
    public Screen screen;
    public TitleScreen titleScreen;
    public CutsceneScreen cutsceneScreen;
    public GameScreen gameScreen;
    public DialogueScreen dialogueScreen;

    public ScreenManager(GamePanel gp, GameSystem system, Player player) {
        this.gp = gp;
        titleScreen = new TitleScreen(gp);
        cutsceneScreen = new CutsceneScreen(gp);
        gameScreen = new GameScreen(gp, system, player);
        this.dialogueScreen = new DialogueScreen(gameScreen);

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
