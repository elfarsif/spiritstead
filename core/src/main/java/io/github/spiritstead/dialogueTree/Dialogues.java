package io.github.spiritstead.dialogueTree;

import io.github.spiritstead.main.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Dialogues {
    public final DialogueNode axeFoundDialog;
    public final DialogueNode helpFindAxe;

    public Dialogues() {
        this.axeFoundDialog = new DialogueNode("you have the axe", DialoguePhase.ADVANCING);
        this.axeFoundDialog.left = new DialogueNode("come see if you can help clear the logs", DialoguePhase.ADVANCING);

        this.helpFindAxe = new DialogueNode(Game.script.mayorDialogue.get(0), DialoguePhase.ADVANCING);
        this.helpFindAxe.left = new DialogueNode(Game.script.mayorDialogue.get(1), DialoguePhase.CHOOSING);
        this.helpFindAxe.right = new DialogueNode(Game.script.mayorDialogue.get(2), DialoguePhase.CHOOSING);
        this.helpFindAxe.left.left = new DialogueNode(Game.script.mayorDialogue.get(3), DialoguePhase.CHOOSINGRESPONSE, new ArrayList<>(Arrays.asList(
                new NoBenifit(),
                new SpawnAxe()
        )));
        this.helpFindAxe.right.left = new DialogueNode(Game.script.mayorDialogue.get(4), DialoguePhase.CHOOSINGRESPONSE, new ArrayList<>(Arrays.asList(
                new IncreaseXP(),
                new SpawnAxe()
        )));

       /* this.testConvo = new DialogueNode("i am mayor who are you", DialoguePhase.ADVANCING);
        this.testConvo.left = new DialogueNode("I have more to say first", DialoguePhase.ADVANCING);
        this.testConvo.left.left = new DialogueNode("I am left", DialoguePhase.CHOOSING);
        this.testConvo.left.right = new DialogueNode("I am right", DialoguePhase.CHOOSING);
        this.testConvo.left.left.left = new DialogueNode("Hello left", DialoguePhase.CHOOSINGRESPONSE, new ArrayList<>(Arrays.asList(
            new SpawnAxe(),
            new IncreaseXP()
        )));
        this.testConvo.left.right.left = new DialogueNode("Hello right", DialoguePhase.CHOOSINGRESPONSE, new ArrayList<>(Arrays.asList(
            new SpawnAxe(),
            new IncreaseXP()
        )));
        this.testConvo.left.left.left.left = new DialogueNode("I am deep in conversation", DialoguePhase.ADVANCING);
   */
    }
}
