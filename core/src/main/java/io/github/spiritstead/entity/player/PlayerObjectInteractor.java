package io.github.spiritstead.entity.player;

import com.badlogic.gdx.Game;
import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.audio.SoundEffect;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.GameObject;

public class PlayerObjectInteractor {
    private Player player;
    private int index;
    private GamePanel gp;
    private AudioPlayer audioPlayer;

    public PlayerObjectInteractor(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.audioPlayer = gp.audioPlayer;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void interact() {
        if (index != 9999) {
            String objName = gp.aSetter.objects[index].name;
            switch (objName) {
                case "Key":
                    player.hasKey++;
                    gp.aSetter.objects[index] = null;
                    this.audioPlayer.playSE(SoundEffect.COIN);
                    gp.ui.gameScreenUI.showMessage("You got a key!");
                    break;
                case "Door":
                    if (player.hasKey > 0) {
                        gp.aSetter.objects[index] = null;
                        player.hasKey--;
                        gp.ui.gameScreenUI.showMessage("You opened the door!");
                    } else {
                        gp.ui.gameScreenUI.showMessage("You need a key");
                    }
                    break;
                case "Boots":
                    player.speed += 2;
                    gp.aSetter.objects[index] = null;
                    this.audioPlayer.playSE(SoundEffect.POWERUP);
                    gp.ui.gameScreenUI.showMessage("YOU ARE FAST");
                    break;
                case "Chest":
                    gp.ui.gameScreenUI.gameFinished = true;
                    this.audioPlayer.playSE(SoundEffect.POWERUP);
                    break;

            }
        }
    }
}
