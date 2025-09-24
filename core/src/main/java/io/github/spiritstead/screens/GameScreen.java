package io.github.spiritstead.screens;

import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.AssetSetter;
import io.github.spiritstead.main.GamePanel;

public class GameScreen implements Screen {
    private GamePanel gp;
    private AssetSetter assetSetter;
    private Player player;

    public GameScreen(GamePanel gp, Player player) {
        this.gp = gp;
        this.assetSetter = gp.aSetter;
        this.player = player;

    }

    @Override
    public void draw() {
        gp.tileM.draw(gp.batch);
        drawObjects();
        drawNPCS();
        player.draw();
        gp.eHandler.draw(gp.batch);
        gp.ui.draw();
    }

    public void update() {
        player.update();
        updateNPCs();

    }

    public void dispose() {
        gp.ui.dispose();
    }

    private void drawNPCS() {
        for (int i = 0; i < gp.aSetter.npcs.length; i++) {
            if (gp.aSetter.npcs[i] != null) {
                gp.aSetter.npcs[i].draw();
            }
        }
    }

    private void drawObjects() {
        for (int i = 0; i < gp.aSetter.objects.length; i++) {
            if (assetSetter.objects[i] != null) {
                assetSetter.objects[i].draw(gp.batch, this.gp);
            }
        }
    }

    private void updateNPCs() {
        for (int i = 0; i < gp.aSetter.npcs.length; i++) {
            if (gp.aSetter.npcs[i] != null) {
                gp.aSetter.npcs[i].update();
            }
        }
    }
}
