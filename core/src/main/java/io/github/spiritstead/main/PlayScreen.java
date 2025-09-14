package io.github.spiritstead.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.ui.UI;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.tile.TileManager;

public class PlayScreen implements Screen {
    GamePanel gp;
    public TileManager tileM;
    public GameObject objects[] = new GameObject[10];
    private AssetSetter aSetter;
    private Entity npcs[] = new Entity[10];
    public Player player;
    public EventHandler eHandler;
    public UI ui;

    public PlayScreen(GamePanel gp) {
        this.gp = gp;
        this.tileM = new TileManager(gp);
        aSetter = new AssetSetter(gp);
        eHandler = new EventHandler(gp);
        ui = new UI(gp);

    }

    public void setObject(GameObject objects[]) {
        this.objects = objects;
    }

    public void setNpcs(Entity npcs[]) {
        this.npcs = npcs;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void draw(SpriteBatch batch) {
        tileM.draw(batch);

        //draw objects
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                objects[i].draw(batch, this.gp);
            }
        }

        //draw NPC
        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i] != null) {
                npcs[i].draw(batch);
            }
        }

        player.draw(batch);

        //draw event rects
        eHandler.draw(batch);

        ui.draw(batch);

    }

    public void dispose() {
        ui.dispose();
    }
}
