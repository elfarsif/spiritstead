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

public class Key implements GameObject {
    public Sprite image;
    public String name;
    public boolean collision = false;
    SolidArea solidArea = new SolidArea(0, 0, 48, 48);
    ObjectDrawer objectDrawer;
    private WorldPosition worldPosition = new WorldPosition();

    public Key() {
        name = "Key";
        image = new Sprite(new Texture("objects/key.png"));
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    public Key(int worldX, int worldY) {
        this();
        worldPosition.setXY(worldX, worldY);
    }

    public void draw(SpriteBatch batch) {
        objectDrawer.draw(image);
    }

    @Override
    public void interact() {
        Game.player.addKey();
        Game.audioPlayer.playSE(SoundEffect.COIN);
        Game.ui.gameUIScreen.showMessage("You got a key!");
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
