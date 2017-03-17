package cf.janga.ds2.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {

    @Test
    public void shouldConnect() throws GraphException {
        Vertex source = new Vertex("1");
        Vertex destination = new Vertex("2");

        Edge edge = source.connect(destination);
        assertSame(source, edge.getSource());
        assertSame(destination, edge.getDestination());

        assertEquals(1, source.getEdges().size());
        assertEquals(0, destination.getEdges().size());

        destination.connect(source);
        assertEquals(1, source.getEdges().size());
        assertEquals(1, destination.getEdges().size());
    }

    @Test
    public void shouldNotConnectTheSameVertex() {
        Vertex vertex1 = new Vertex("1");
        try {
            Edge edge = vertex1.connect(vertex1);
            fail();
        } catch (GraphException e) {
        }
    }
}
