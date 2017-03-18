package cf.janga.ds2.model.net;

/**
 * Represents a directional connection between two nodes in a graph.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Link extends NetworkElementImpl {

    private final Node source_;

    private final Node destination_;

    /**
     * Creates a new {@link Link}.
     *
     * @param name        Name of this link.
     * @param source      The source node.
     * @param destination The destination node.
     */
    Link(String name, Node source, Node destination) {
        super(name);
        source_ = source;
        destination_ = destination;
    }

    public Node getSource() {
        return source_;
    }

    public Node getDestination() {
        return destination_;
    }
}
