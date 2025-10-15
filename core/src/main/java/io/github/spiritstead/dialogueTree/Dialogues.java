package io.github.spiritstead.dialogueTree;

import io.github.spiritstead.main.Game;

public class Dialogues {
    public final Node axeFoundDialog;
    public final Node helpFindAxe;

    public Dialogues() {
        this.axeFoundDialog =
                Node.builder().text("you have the axe").phase(DialoguePhase.ADVANCING)
                        .left(
                                Node.builder().text("come see if you can help clear the logs").phase(DialoguePhase.ADVANCING).build()
                        ).build();

        this.helpFindAxe =
                Node.builder().text(Game.script.mayorDialogue.get(0)).phase(DialoguePhase.ADVANCING)
                        .left(
                                Node.builder().text(Game.script.mayorDialogue.get(1)).phase(DialoguePhase.CHOOSING)
                                        .left(
                                                Node.builder().text(Game.script.mayorDialogue.get(3)).phase(DialoguePhase.CHOOSINGRESPONSE)
                                                        .addEvent(new NoBenifit()).build()
                                        ).build()
                        )
                        .right(
                                Node.builder().text(Game.script.mayorDialogue.get(2)).phase(DialoguePhase.CHOOSING)
                                        .left(
                                                Node.builder().text(Game.script.mayorDialogue.get(4)).phase(DialoguePhase.CHOOSINGRESPONSE)
                                                        .addEvent(new IncreaseXP())
                                                        .addEvent(new SpawnAxe()).build()
                                        ).build()
                        ).build();

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
