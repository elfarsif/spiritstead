package io.github.spiritstead.screens;

import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.AssetSetter;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class GameScreen implements Screen {
    private GamePanel gp;
    private AssetSetter assetSetter;
    private Player player;

    public GameScreen(GamePanel gp, Player player) {
        this.gp = gp;
        this.assetSetter = Game.aSetter;
        this.player = player;

    }

    @Override
    public void draw() {
        Game.tileM.draw(Game.batch);
        drawObjects();
        drawNPCS();
        player.draw();
        Game.eHandler.draw(Game.batch);
        Game.ui.draw();
    }

    public void update() {
        player.update();
        updateNPCs();
    }

    public void dispose() {
        Game.ui.dispose();
    }

    private void drawNPCS() {
        for (int i = 0; i < Game.aSetter.npcs.length; i++) {
            if (Game.aSetter.npcs[i] != null) {
                Game.aSetter.npcs[i].draw();
            }
        }
    }

    private void drawObjects() {
        for (int i = 0; i < Game.aSetter.objects.length; i++) {
            if (assetSetter.objects[i] != null) {
                assetSetter.objects[i].draw(Game.batch, this.gp);
            }
        }
    }

    private void updateNPCs() {
        for (int i = 0; i < Game.aSetter.npcs.length; i++) {
            if (Game.aSetter.npcs[i] != null) {
                Game.aSetter.npcs[i].update();
            }
        }
    }
}
