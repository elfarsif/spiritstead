package io.github.spiritstead.screens;

import io.github.spiritstead.entity.Drawable;
import io.github.spiritstead.entity.Updatable;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.AssetSetter;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.GameSystem;

public class GameScreen implements Screen {
    private GamePanel gp;
    private GameSystem system;
    private AssetSetter assetSetter;
    private Player player;
    private Updatable playerUpdate;
    private Drawable drawablePlayer;

    public GameScreen(GamePanel gp, GameSystem system, Player player) {
        this.gp = gp;
        this.system = system;
        this.assetSetter = system.aSetter;
        this.player = player;
        this.playerUpdate = player;
        this.drawablePlayer = player;

    }

    @Override
    public void draw() {
        system.tileM.draw(gp.batch);
        drawObjects();
        drawNPCS();
        this.drawablePlayer.draw();
        system.eHandler.draw(gp.batch);
        system.ui.draw();
    }

    public void update() {
        playerUpdate.update();
        updateNPCs();

    }

    public void dispose() {
        system.ui.dispose();
    }

    private void drawNPCS() {
        for (int i = 0; i < assetSetter.npcs.length; i++) {
            if (assetSetter.npcs[i] != null) {
                assetSetter.npcs[i].draw();
            }
        }
    }

    private void drawObjects() {
        for (int i = 0; i < assetSetter.objects.length; i++) {
            if (assetSetter.objects[i] != null) {
                assetSetter.objects[i].draw(gp.batch, this.gp);
            }
        }
    }

    private void updateNPCs() {
        for (int i = 0; i < assetSetter.npcs.length; i++) {
            if (assetSetter.npcs[i] != null) {
                assetSetter.npcs[i].update();
            }
        }
    }
}
