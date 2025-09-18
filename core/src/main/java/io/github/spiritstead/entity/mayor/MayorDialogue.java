package io.github.spiritstead.entity.mayor;

public class MayorDialogue {
    public String[] array = new String[20];
    public int index = 0;

    public MayorDialogue() {
        initializeDialogues();
    }

    private void initializeDialogues() {
        array[0] = "Hello, lad";
        array[1] = "text 2";
        array[2] = "text 3";
        array[3] = "text 4";
    }
}
