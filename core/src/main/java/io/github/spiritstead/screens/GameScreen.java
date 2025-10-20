package io.github.spiritstead.screens;

import io.github.spiritstead.entity.NPC;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.DayCycle;
import io.github.spiritstead.main.EventHandler;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.tile.TileManager;
import io.github.spiritstead.tools.BlackAlphaFrames;
import io.github.spiritstead.tools.FrameGate;
import io.github.spiritstead.ui.UI;

import java.util.List;

public class GameScreen implements Screen {
    private final Player player;
    private final NPC[] npcs;
    private final List<GameObject> obj;
    private final TileManager tileM;
    private final EventHandler eHandler;
    private final UI ui;
    private final DayCycle dayCycle;

    public GameScreen(Player player, NPC[] npcs, List<GameObject> obj, TileManager tileM, EventHandler eHandler, UI ui) {
        this.player = player;
        this.npcs = npcs;
        this.obj = obj;
        this.tileM = tileM;
        this.eHandler = eHandler;
        this.ui = ui;
        this.dayCycle = new DayCycle(new FrameGate(60), new BlackAlphaFrames());
    }

    @Override
    public void draw() {
        this.tileM.draw(Game.batch);
        this.drawObjects();
        this.drawNPCS();
        this.player.draw();
        this.eHandler.draw(Game.batch);
        this.ui.draw();
        this.dayCycle.draw();
    }

    public void update() {
        this.player.update();
        this.updateNPCs();
    }

    public void dispose() {
        this.ui.dispose();
    }

    private void drawNPCS() {
        for (int i = 0; i < this.npcs.length; i++) {
            if (this.npcs[i] != null) {
                this.npcs[i].draw();
            }
        }
    }

    private void drawObjects() {
        for (int i = 0; i < this.obj.size(); i++) {
            if (this.obj.get(i) != null) {
                this.obj.get(i).draw(Game.batch);
            }
        }
    }

    private void updateNPCs() {
        for (int i = 0; i < this.npcs.length; i++) {
            if (this.npcs[i] != null) {
                this.npcs[i].update();
            }
        }
    }

}
