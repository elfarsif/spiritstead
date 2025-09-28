package io.github.spiritstead.dialogue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Dialogue {
    public DialogueNode node;
    ArrayList<ArrayList<String>> dialogues = new ArrayList<>();

    public Dialogue() {
        dialogues.add(new ArrayList<>(Arrays.asList("M1")));
        dialogues.add(new ArrayList<>(Arrays.asList("PO1", "Po2k")));
        dialogues.add(new ArrayList<>(Arrays.asList("M2PO1", "M2PO2")));

        node = new DialogueNode("M1");
        node.left = new DialogueNode("PO1");
        node.right = new DialogueNode("PO2");
        node.left.left = new DialogueNode("M2 from PO1");
        node.right.left = new DialogueNode("M2 from PO2");

    }

}
