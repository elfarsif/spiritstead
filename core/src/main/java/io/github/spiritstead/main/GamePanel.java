package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.cutscene.CutsceneScreen;
import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.collision.CollisionChecker;
import io.github.spiritstead.main.ui.TitleScreen;
import io.github.spiritstead.main.ui.UI;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.script.Script;
import io.github.spiritstead.tile.TileManager;

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
    public TileManager tileM;
    public KeyHandler keyH;
    SoundWrapper music;
    SoundWrapper se;
    public CollisionChecker cChecker;
    public AssetSetter aSetter;
    public Script script = new Script();

    //Entities and Objects
    public Player player;
    public GameObject objects[] = new GameObject[10];
    public Entity npcs[] = new Entity[10];

    public GameState gameState;

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
        tileM = new TileManager(this);
        aSetter = new AssetSetter(this);

        titleScreen = new TitleScreen(this);
        cutsceneScreen = new CutsceneScreen(this);
        playScreen = new PlayScreen(this);

        player = new Player(this, keyH);
        playScreen.setPlayer(player);

        setupGame();

        Gdx.input.setInputProcessor(keyH);
    }

    private void setupGame() {
        aSetter.setObject();
        playScreen.setObject(objects);
        aSetter.setNPCs();
        playScreen.setNpcs(npcs);

//        playMusic(0);
        gameState = gameState.TITLESTATE;
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
        switch (gameState) {
            case PLAYSTATE:
                player.update();

                //NPC update
                for (int i = 0; i < npcs.length; i++) {
                    if (npcs[i] != null) {
                        npcs[i].update();
                    }
                }

                break;
            case PAUSESTATE:
                break;
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
