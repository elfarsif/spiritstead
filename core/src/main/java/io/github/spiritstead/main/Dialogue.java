package io.github.spiritstead.main;

import io.github.spiritstead.dialogue.*;

public class Dialogue {
    public final Node axeFoundDialog;
    public final Node helpFindAxe;

    public Dialogue() {
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

    }
}
