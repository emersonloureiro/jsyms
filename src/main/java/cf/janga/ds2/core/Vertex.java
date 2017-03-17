package cf.janga.ds2.core;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the nodes in a graph.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class Vertex extends GraphElementImpl {

    /**
     * Holds the edges connected to this vertex.
     */
    private final List<Edge> edges_;

    /**
     * Creates a new {@link Vertex}.
     *
     * @param name Name of this {@link Vertex}.
     */
    public Vertex(String name) {
        super(name);
        this.edges_ = new LinkedList<Edge>();
    }

    /**
     * Connects this vertex with the one provided, and assumes that this
     * vertex is the source and the one given the destination.
     *
     * @param destination The destination vertex to connect to this one
     * @return
     * @throws GraphException If the vertex provided is the same as this one
     */
    public Edge connect(Vertex destination) throws GraphException {
        if (destination == this) {
            throw new GraphException("Vertices cannot be the same");
        }
        String edgeName = getName() + "-" + destination.getName();
        Edge newEdge = new Edge(edgeName, this, destination);
        edges_.add(newEdge);
        return newEdge;
    }

    List<Edge> getEdges() {
        return edges_;
    }
}
