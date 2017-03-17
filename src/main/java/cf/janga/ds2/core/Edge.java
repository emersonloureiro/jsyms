package cf.janga.ds2.core;

/**
 * Represents edges between the vertices in a graph.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Edge extends GraphElementImpl {

    /**
     * One of the vertices linked by this edge.
     */
    private Vertex vertexA_;

    /**
     * One of the vertices linked by this edge.
     */
    private Vertex vertexB_;

    /**
     * Creates a new {@link Edge}.
     *
     * @param name Name of this edge.
     * @param a    One of the vertices linked by this edge.
     * @param b    The other vertex linked by this edge.
     */
    public Edge(final String name, final Vertex a, final Vertex b) {
        super(name);
        this.vertexA_ = a;
        this.vertexB_ = b;

        a.addEdge(this);
        b.addEdge(this);
    }
}
