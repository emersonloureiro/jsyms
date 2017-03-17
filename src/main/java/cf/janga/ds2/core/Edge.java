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
    private final Vertex source_;

    /**
     * One of the vertices linked by this edge.
     */
    private final Vertex destination_;

    /**
     * Creates a new {@link Edge}.
     *
     * @param name        Name of this edge.
     * @param source      The source vertex.
     * @param destination The destination vertex.
     */
    Edge(String name, Vertex source, Vertex destination) {
        super(name);
        source_ = source;
        destination_ = destination;
    }

    public Vertex getSource() {
        return source_;
    }

    public Vertex getDestination() {
        return destination_;
    }
}
