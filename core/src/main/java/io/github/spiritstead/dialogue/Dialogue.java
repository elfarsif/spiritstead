package io.github.spiritstead.dialogue;

import io.github.spiritstead.main.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Dialogue {
    public DialogueNode node;
//    ArrayList<ArrayList<String>> dialogues = new ArrayList<>();

    public Dialogue() {
        /*dialogues.add(new ArrayList<>(Arrays.asList("M1")));
        dialogues.add(new ArrayList<>(Arrays.asList("PO1", "Po2k")));
        dialogues.add(new ArrayList<>(Arrays.asList("M2PO1", "M2PO2")));
*/
        node = new DialogueNode(Game.script.mayorDialogue.get(0));
        node.left = new DialogueNode(Game.script.mayorDialogue.get(1));
        node.right = new DialogueNode(Game.script.mayorDialogue.get(2));
        node.left.left = new DialogueNode(Game.script.mayorDialogue.get(3));
        node.right.left = new DialogueNode(Game.script.mayorDialogue.get(4));

    }

}
