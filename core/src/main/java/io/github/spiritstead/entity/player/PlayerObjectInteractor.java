package io.github.spiritstead.entity.player;

import com.badlogic.gdx.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.GameObject;

public class PlayerObjectInteractor {
    private Player player;
    private int index;
    private GamePanel gp;

    public PlayerObjectInteractor(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void interact() {
        if (index != 9999) {
            String objName = gp.system.aSetter.objects[index].name;
            switch (objName) {
                case "Key":
                    player.hasKey++;
                    gp.system.aSetter.objects[index] = null;
                    gp.system.audioPlayer.playSE(1);
                    gp.system.ui.gameScreenUI.showMessage("You got a key!");
                    break;
                case "Door":
                    if (player.hasKey > 0) {
                        gp.system.aSetter.objects[index] = null;
                        player.hasKey--;
                        gp.system.ui.gameScreenUI.showMessage("You opened the door!");
                    } else {
                        gp.system.ui.gameScreenUI.showMessage("You need a key");
                    }
                    break;
                case "Boots":
                    player.speed += 2;
                    gp.system.aSetter.objects[index] = null;
                    gp.system.audioPlayer.playSE(2);
                    gp.system.ui.gameScreenUI.showMessage("YOU ARE FAST");
                    break;
                case "Chest":
                    gp.system.ui.gameScreenUI.gameFinished = true;
                    gp.system.audioPlayer.playSE(2);
                    break;

            }
        }
    }
}
