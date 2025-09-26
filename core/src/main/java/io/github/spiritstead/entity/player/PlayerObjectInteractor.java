package io.github.spiritstead.entity.player;

import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.audio.SoundEffect;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class PlayerObjectInteractor {
    private Player player;
    private int index;
    private GamePanel gp;
    private AudioPlayer audioPlayer;

    public PlayerObjectInteractor(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.audioPlayer = Game.audioPlayer;
    }

    public void interact(int index) {
        this.index = index;
        if (this.index != 9999) {
            String objName = Game.aSetter.objects[this.index].name;
            switch (objName) {
                case "Key":
                    player.hasKey++;
                    Game.aSetter.objects[this.index] = null;
                    this.audioPlayer.playSE(SoundEffect.COIN);
                    Game.ui.gameScreenUI.showMessage("You got a key!");
                    break;
                case "Door":
                    if (player.hasKey > 0) {
                        Game.aSetter.objects[this.index] = null;
                        player.hasKey--;
                        Game.ui.gameScreenUI.showMessage("You opened the door!");
                    } else {
                        Game.ui.gameScreenUI.showMessage("You need a key");
                    }
                    break;
                case "Boots":
                    player.speed += 2;
                    Game.aSetter.objects[this.index] = null;
                    this.audioPlayer.playSE(SoundEffect.POWERUP);
                    Game.ui.gameScreenUI.showMessage("YOU ARE FAST");
                    break;
                case "Chest":
                    Game.ui.gameScreenUI.gameFinished = true;
                    this.audioPlayer.playSE(SoundEffect.POWERUP);
                    break;

            }
        }
    }
}
