package cf.janga.ds2.core;

import java.util.Collection;
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
    private List<Edge> edges_;

    /**
     * Creates a new {@link Vertex}.
     *
     * @param name Name of this {@link Vertex}.
     */
    public Vertex(final String name) {
        super(name);
        this.edges_ = new LinkedList<Edge>();
    }

    /**
     * Returns the edges of this vertex.
     *
     * @return a {@link Collection} of {@link Edge} objects.
     */
    public final List<Edge> getEdges() {
        return this.edges_;
    }

    /**
     * Adds an edge to this vertex.
     *
     * @param edge Edge to be added.
     */
    final void addEdge(final Edge edge) {
        if (!this.edges_.contains(edge)) {
            this.edges_.add(edge);
        }
    }

    /**
     * Removes the provided from this vertex.
     *
     * @param edge The edge to be removed.
     */
    public final void removeEdge(final Edge edge) {
        this.edges_.remove(edge);
    }
}
