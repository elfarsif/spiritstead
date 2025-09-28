import io.github.spiritstead.dialogue.Dialogue;
import io.github.spiritstead.dialogue.DialogueNode;
import org.junit.Assert;
import org.junit.Test;

public class DialogueNodeTest {

    @Test
    public void NodeTest() {
        DialogueNode node = new DialogueNode("Mayor 1");
        DialogueNode node2 = new DialogueNode("PO1");
        DialogueNode node3 = new DialogueNode("PO2");
        node.left = node2;
        node.right = node3;

        Assert.assertEquals("PO1", node.left.dialogue);
        Assert.assertEquals("PO2", node.right.dialogue);

    }

    @Test
    public void dialogueTest() {
        Dialogue dialogue = new Dialogue();
    }
}
