package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.cutscene.CutsceneScreen;
import io.github.spiritstead.main.collision.CollisionChecker;
import io.github.spiritstead.main.ui.TitleScreen;
import io.github.spiritstead.script.Script;

/*
This class is the main class that handles all aspects of game logic
 */
public class GamePanel extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    //Screen Setting
    final int orginalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = orginalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World Setting
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;

    //System
    public KeyHandler keyH;
    SoundWrapper music;
    SoundWrapper se;
    public CollisionChecker cChecker;
    public Script script = new Script();

    public Screen screen;
    public TitleScreen titleScreen;
    public CutsceneScreen cutsceneScreen;
    public PlayScreen playScreen;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);

        batch = new SpriteBatch();
        keyH = new KeyHandler(this);
        music = new SoundWrapper();
        se = new SoundWrapper();
        cChecker = new CollisionChecker(this);

        titleScreen = new TitleScreen(this);
        cutsceneScreen = new CutsceneScreen(this);
        playScreen = new PlayScreen(this, keyH);

        setupGame();

        Gdx.input.setInputProcessor(keyH);
    }

    private void setupGame() {

//        playMusic(0);
        screen = titleScreen;

    }

    @Override
    public void resize(int width, int height) {
        // Resize the default camera viewport to match the new window size
        // This is necessary to ensure that the game scales correctly when the window is resized.
        Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
//        se.play();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        update();
        draw();
        batch.end();
    }

    private void update() {
        if (screen == playScreen) {
            playScreen.update();
        }

    }

    private void draw() {
        screen.draw(batch);
    }

    @Override
    public void dispose() {
        titleScreen.dispose();
        playScreen.dispose();
        batch.dispose();
    }
}
