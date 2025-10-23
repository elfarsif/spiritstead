package io.github.spiritstead.screens;

import io.github.spiritstead.entity.Npcs;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.DayCycle;
import io.github.spiritstead.main.EventHandler;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.object.GameObjects;
import io.github.spiritstead.tile.TileManager;
import io.github.spiritstead.ui.UI;

public class GameScreen implements Screen {
    private final Player player;
    private final Npcs npcs;
    private final GameObjects objects;
    private final TileManager tileM;
    private final EventHandler eHandler;
    private final UI ui;
    private final DayCycle dayCycle;

    public GameScreen(Player player, Npcs npcs, GameObjects objects, TileManager tileM, EventHandler eHandler, UI ui,
                      DayCycle dayCycle) {
        this.player = player;
        this.npcs = npcs;
        this.objects = objects;
        this.tileM = tileM;
        this.eHandler = eHandler;
        this.ui = ui;
        this.dayCycle = dayCycle;
    }

    @Override
    public void draw() {
        this.tileM.draw(Game.batch);
        this.objects.draw();
        this.npcs.draw();
        this.player.draw();
        this.eHandler.draw(Game.batch);
        this.ui.draw();
//        this.dayCycle.draw();
    }

    public void update() {
        this.player.update();
        this.npcs.update();
    }

    public void dispose() {
        this.ui.dispose();
    }

}
