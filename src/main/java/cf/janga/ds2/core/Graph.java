package cf.janga.ds2.core;

/**
 * A composite element representing a graph, with edges and vertices, which in
 * turn models the topology of a {@link Network}.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Graph extends GraphElementImpl {

    /**
     * Creates a new <code>Graph</code>.
     * @param name Name of this {@code Graph}.
     */
    public Graph(final String name) {
        super(name);
    }
}
