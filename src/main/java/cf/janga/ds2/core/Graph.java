package cf.janga.ds2.core;

import java.util.List;

/**
 * A composite element representing a graph, with vertices connected to each other, which in
 * turn models the topology of a distributed system.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Graph extends GraphElementImpl {

    private final List<Vertex> vertices_;

    /**
     * Creates a new <code>Graph</code>.
     *
     * @param name Name of this {@code Graph}.
     */
    public Graph(String name, List<Vertex> vertices) {
        super(name);
        vertices_ = vertices;
    }

    /**
     * Returns the list of vertices of this graph.
     *
     * @return <code>List</code> of <code>Vertex</code> objects
     */
    public List<Vertex> getVertices_() {
        return vertices_;
    }
}
