package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.tile.TileManager;

/*
This class is the main class that handles all aspects of game logic
 */
public class GamePanel extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    //Screen Setting
    final int orginalTileSize =16;
    final int scale = 3;

    public final int tileSize = orginalTileSize*scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    TileManager tileM;
    KeyHandler keyH;
    Player player;


    @Override
    public void create() {

        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        batch = new SpriteBatch();
        keyH = new KeyHandler();
        tileM = new TileManager(this);
        player = new Player(this, keyH);

        Gdx.input.setInputProcessor(keyH);
    }

    @Override
    public void resize(int width, int height) {
        // Resize the default camera viewport to match the new window size
        // This is necessary to ensure that the game scales correctly when the window is resized.
        Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
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
        player.update();
    }

    private void draw() {
        tileM.draw(batch);
        player.draw(batch);
    }

    @Override
    public void dispose() {
    }
}
