package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.audio.SoundEffect;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;

public class Boots implements GameObject {
    private static final String FILE_PATH = "objects/boots.png";
    private static final int SOLIDAREA_WIDTH = 48;
    private static final int SOLIDAREA_HEIGHT = 48;

    private final Sprite image;
    private final WorldPosition worldPosition = new WorldPosition();
    private final SolidArea solidArea = new SolidArea(0, 0, SOLIDAREA_WIDTH, SOLIDAREA_HEIGHT);
    private final ObjectDrawer objectDrawer;

    public Boots() {
        image = new Sprite(new Texture(FILE_PATH));
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    public Boots(int worldX, int worldY) {
        this();
        worldPosition.setXY(worldX, worldY);
    }

    public void draw(SpriteBatch batch) {
        objectDrawer.draw(image);
    }

    @Override
    public void interact() {
        Game.player.increaseSpeedBy(2);
        Game.audioPlayer.playSE(SoundEffect.POWERUP);
        Game.ui.gameUIScreen.showMessage("YOU ARE FAST");
        for (int i = 0; i < Game.aSetter.gameObjects.size(); i++) {
            if (Game.aSetter.gameObjects.get(i) == this) {
                Game.aSetter.gameObjects.remove(i);
            }
        }
    }
    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public Sprite getImage() { return this.image; }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public int getSpeed() { return 0; }
    @Override
    public Direction getDirection() { return null; }
}
