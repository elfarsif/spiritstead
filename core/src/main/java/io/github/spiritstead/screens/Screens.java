package io.github.spiritstead.screens;

import com.badlogic.gdx.Gdx;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.screens.titleScreen.TitleScreen;
import io.github.spiritstead.screens.titleScreen.TitleScreenOptions;
import io.github.spiritstead.tools.BlackTexture;
import io.github.spiritstead.tools.OptionCursor;
import io.github.spiritstead.tools.Options;

import java.util.ArrayList;
import java.util.Arrays;

public class Screens {
    private GamePanel gp;
    public Screen screen;
    public TitleScreen titleScreen;
    public CutsceneScreen cutsceneScreen;
    public GameScreen gameScreen;
    public DialogueScreen dialogueScreen;

    public Screens(GamePanel gp, Player player) {
        this.gp = gp;
        this.titleScreen = new TitleScreen(
                new Font("fonts/maruMonicaBold.fnt"),
                new Font("fonts/alagard_60.fnt"),
                new BlackTexture(ScreenSetting.SCREEN_WIDTH, ScreenSetting.SCREEN_HEIGHT),
                new OptionCursor(new Font("fonts/maruMonicaBold.fnt")),
                new Options(new Font("fonts/maruMonicaBold.fnt"),
                        ScreenSetting.TILE_SIZE,
                        new ArrayList<String>(Arrays.asList(
                                TitleScreenOptions.NEW_GAME.getString(),
                                TitleScreenOptions.LOAD_GAME.getString(),
                                TitleScreenOptions.QUIT.getString()
                        )))
        );
        this.cutsceneScreen = new CutsceneScreen(gp);
        this.gameScreen = new GameScreen(gp, player);
        this.dialogueScreen = new DialogueScreen(gameScreen);
        setScreen(this.titleScreen);
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void drawScreen() {
        screen.draw();
    }

    public void dispose() {
        titleScreen.dispose();
        gameScreen.dispose();
    }

}
