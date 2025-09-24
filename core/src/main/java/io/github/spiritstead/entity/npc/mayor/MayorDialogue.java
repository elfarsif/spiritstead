package io.github.spiritstead.entity.npc.mayor;

import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;

import java.util.HashMap;
import java.util.Map;

public class MayorDialogue {
    public Map<Integer, String> array = new HashMap<>();
    private int index = 0;
    private int width;
    private TextWrapper textWrapper;
    private Font font;

    public MayorDialogue(Font font, int width) {
        this.font = font;
        this.width = width;
        initializeDialogues();
        wrapDialogues();
    }

    private void wrapDialogues() {
        textWrapper = new TextWrapper(font, this.width);
        for (int i = 0; i < array.size(); i++) {
            textWrapper.setText(array.get(i));
            textWrapper.wrap();
            array.put(i, textWrapper.wrappedText);
        }
    }

    private void initializeDialogues() {
        array.put(0, "Don’t just stand there, come give me a hand, there should be another axe around here somewhere");
        array.put(1, "text 2");
        array.put(2, "text 3");
        array.put(3, "text 4");
    }

    public void increment() {
        this.index++;
    }

    public String getCurrentDialogue() {
        return this.array.get(this.index);
    }
}
