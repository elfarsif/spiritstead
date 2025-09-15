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
    public AssetSetter aSetter;
    public Player player;
    public EventHandler eHandler;
    public UI ui;

    public PlayScreen(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.tileM = new TileManager(gp);
        eHandler = new EventHandler(gp);
        ui = new UI(gp);
        aSetter = new AssetSetter(gp);
        player = new Player(gp, keyH);

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void draw(SpriteBatch batch) {
        tileM.draw(batch);

        //draw objects
        for (int i = 0; i < aSetter.objects.length; i++) {
            if (aSetter.objects[i] != null) {
                aSetter.objects[i].draw(batch, this.gp);
            }
        }

        //draw NPC
        for (int i = 0; i < aSetter.npcs.length; i++) {
            if (aSetter.npcs[i] != null) {
                aSetter.npcs[i].draw(batch);
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

    public void update() {
        player.update();

        //NPC update
        for (int i = 0; i < aSetter.npcs.length; i++) {
            if (aSetter.npcs[i] != null) {
                aSetter.npcs[i].update();
            }
        }

    }
}
