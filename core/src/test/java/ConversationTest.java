import io.github.spiritstead.dialogue.Conversation;
import io.github.spiritstead.dialogue.DialoguePhase;
import io.github.spiritstead.dialogue.Node;
import org.junit.Assert;
import org.junit.Test;

public class ConversationTest {
    Node node;
    Conversation convo;

    public ConversationTest() {
        this.node = Node.builder().text("test").phase(DialoguePhase.ADVANCING)
                .left(Node.builder().text("continue")
                        .left(Node.builder().text("choice 1")
                                .left(Node.builder().text("choice 1.1").build()).build())
                        .right(Node.builder().text("choice 2").build()).build()
                ).build();
        this.convo = new Conversation(node);
    }
    @Test
    public void goThroughConversationRight() {
        convo.proceed();
        Assert.assertEquals("test", convo.getLine());
        convo.proceed();
        Assert.assertEquals("continue", convo.getLine());
        convo.makeChoice("right");
        convo.proceed();
        Assert.assertEquals("choice 2", convo.getLine());

    }

    @Test
    public void goThroughConversationLeft() {
        convo.proceed();
        Assert.assertEquals("test", convo.getLine());
        convo.proceed();
        Assert.assertEquals("continue", convo.getLine());
        convo.makeChoice("left");
        convo.proceed();
        Assert.assertEquals("choice 1", convo.getLine());
        convo.proceed();
        Assert.assertEquals("choice 1.1", convo.getLine());

    }

}
