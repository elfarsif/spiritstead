package io.github.spiritstead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.AssetSetter;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.screens.titleScreen.TitleScreenOptions;
import io.github.spiritstead.tools.BlackAlphaFrames;
import io.github.spiritstead.tools.FrameGate;

public class GameScreen implements Screen {
    private GamePanel gp;
    private AssetSetter assetSetter;
    private Player player;
    //Day Night
    private int frameCounter = 0;
    private FrameGate fadeFrameGate;
    private BlackAlphaFrames blackAlphaFrames;

    public GameScreen(GamePanel gp, Player player) {
        this.gp = gp;
        this.assetSetter = Game.aSetter;
        this.player = player;
        //Day and night
        this.fadeFrameGate = new FrameGate(60);
        blackAlphaFrames = new BlackAlphaFrames();
        frameCounter = blackAlphaFrames.frames.size() - 1;
    }

    @Override
    public void draw() {
        Game.tileM.draw(Game.batch);
        drawObjects();
        drawNPCS();
        player.draw();
        Game.eHandler.draw(Game.batch);
        Game.ui.draw();
//        drawDayNightCycle();
    }

    private void drawDayNightCycle() {
        if (fadeFrameGate.tick() && frameCounter > 3) {
            frameCounter--;
            fadeFrameGate.reset();
        }

        Game.batch.draw(blackAlphaFrames.frames.get(frameCounter), 0, 0);
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
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (assetSetter.obj.get(i) != null) {
                assetSetter.obj.get(i).draw(Game.batch, this.gp);
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
