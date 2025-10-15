package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.audio.SoundEffect;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class Boots implements GameObject {
    private static final String FILE_PATH = "objects/boots.png";
    private static final int SOLIDAREA_WIDTH = 48;
    private static final int SOLIDAREA_HEIGHT = 48;

    private Sprite image;
    private WorldPosition worldPosition = new WorldPosition();
    public boolean collision = false;
    SolidArea solidArea = new SolidArea(0, 0, SOLIDAREA_WIDTH, SOLIDAREA_HEIGHT);
    private ObjectDrawer objectDrawer;

    public Boots() {
        image = new Sprite(new Texture(FILE_PATH));
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    public Boots(int worldX, int worldY) {
        this();
        worldPosition.setXY(worldX, worldY);
    }

    public void draw(SpriteBatch batch, GamePanel gp) {
        objectDrawer.draw(image);
    }

    @Override
    public void interact() {
        Game.player.speed += 2;
        Game.audioPlayer.playSE(SoundEffect.POWERUP);
        Game.ui.gameUIScreen.showMessage("YOU ARE FAST");
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) == this) {
                Game.aSetter.obj.remove(i);
            }
        }
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }
    @Override
    public Sprite getImage() {
        return this.image;
    }
    @Override
    public void setCollisionOn(boolean collisionOn) {

    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Direction getDirection() {
        return null;
    }
}
