package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.tile.TileManager;

/*
This class is the main class that handles all aspects of game logic
 */
public class GamePanel extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    //Screen Setting
    final int orginalTileSize =16;
    public final int scale = 3;

    public final int tileSize = orginalTileSize*scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World Setting
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    TileManager tileM;
    KeyHandler keyH;
    public CollisionChecker cChecker;
    public AssetSetter aSetter;

    public Player player;
    public GameObject objects[] = new GameObject[10];


    @Override
    public void create() {

        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        batch = new SpriteBatch();
        keyH = new KeyHandler();
        cChecker = new CollisionChecker(this);
        tileM = new TileManager(this);
        aSetter = new AssetSetter(this);
        player = new Player(this, keyH);

        setupGame();

        Gdx.input.setInputProcessor(keyH);
    }

    private void setupGame(){
        aSetter.setObject();
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

        //draw objects
        for(int i = 0; i<objects.length;i++){
            if (objects[i]!=null){
                objects[i].draw(batch,this);
            }
        }

        player.draw(batch);
    }

    @Override
    public void dispose() {
    }
}
