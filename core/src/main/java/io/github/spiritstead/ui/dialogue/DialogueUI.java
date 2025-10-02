package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.BlackTexture;
import io.github.spiritstead.ui.UIScreen;

public class DialogueUI implements UIScreen {
    private DialogueWindow dialogueWindow;
    public DialogueUIText text;

    public DialogueUI() {
        this.dialogueWindow = new DialogueWindow(
            ScreenSetting.TILE_SIZE * 2,
            ScreenSetting.TILE_SIZE / 2,
            ScreenSetting.SCREEN_WIDTH - ScreenSetting.TILE_SIZE * 4,
            ScreenSetting.TILE_SIZE * 3);
        this.text = new DialogueUIText(dialogueWindow);
    }

    @Override
    public void draw() {
        dialogueWindow.draw();
        text.draw();
    }

    public void dispose() {
        dialogueWindow.dispose();
        text.dispose();
    }

}
