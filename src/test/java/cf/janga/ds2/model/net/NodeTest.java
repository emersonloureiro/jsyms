package cf.janga.ds2.model.net;

import cf.janga.ds2.model.ModelException;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void shouldConnect() throws ModelException {
        Node source = new Node("1");
        Node destination = new Node("2");

        Link link = source.connect(destination);
        assertSame(source, link.getSource());
        assertSame(destination, link.getDestination());

        assertEquals(1, source.getLinks().size());
        assertEquals(0, destination.getLinks().size());

        destination.connect(source);
        assertEquals(1, source.getLinks().size());
        assertEquals(1, destination.getLinks().size());
    }

    @Test
    public void shouldNotConnectTheSameVertex() {
        Node node1 = new Node("1");
        try {
            node1.connect(node1);
            fail();
        } catch (ModelException e) {
        }
    }
}
