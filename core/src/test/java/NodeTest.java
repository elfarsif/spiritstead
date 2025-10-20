import io.github.spiritstead.dialogue.DialoguePhase;
import io.github.spiritstead.dialogue.Node;
import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void NodeTest() {
        Node node1 = Node.builder().text("there").build();
        Node node2 = Node.builder().text("node2").build();
        Node node3 = Node.builder().text("node3").build();
        Node node4 = Node.builder().text("node4").build();

        Node node = Node.builder().text("test").phase(DialoguePhase.ADVANCING)
                .left(Node.builder().text("left").build())
                .right(Node.builder().text("right").build())
                .build();
        Assert.assertEquals("left", node.getLeft().getText());
        Assert.assertEquals("right", node.getRight().getText());
        Assert.assertEquals("left", node.nextLeft().getText());
    }

}
