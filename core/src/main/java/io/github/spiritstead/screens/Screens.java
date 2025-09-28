package io.github.spiritstead.screens;

import com.badlogic.gdx.Gdx;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.screens.titleScreen.TitleScreen;

public class Screens {
    private GamePanel gp;
    public Screen screen;
    public TitleScreen titleScreen;
    public CutsceneScreen cutsceneScreen;
    public GameScreen gameScreen;
    public DialogueScreen dialogueScreen;

    public Screens(GamePanel gp, Player player) {
        this.gp = gp;
        titleScreen = new TitleScreen(gp);
        cutsceneScreen = new CutsceneScreen(gp);
        gameScreen = new GameScreen(gp, player);
        this.dialogueScreen = new DialogueScreen(gameScreen);
        setScreen(titleScreen);
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        Gdx.input.setInputProcessor(screen);
    }

    public void drawScreen() {
        screen.draw();
    }

    public void dispose() {
        titleScreen.dispose();
        gameScreen.dispose();
    }

}
