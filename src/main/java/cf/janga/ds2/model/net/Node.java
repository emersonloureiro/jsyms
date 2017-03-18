package cf.janga.ds2.model.net;

import cf.janga.ds2.model.ModelException;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the nodes in a network.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class Node extends NetworkElementImpl {

    private final List<Link> links_;

    /**
     * Creates a new {@link Node}.
     *
     * @param name Name of this {@link Node}.
     */
    public Node(String name) {
        super(name);
        this.links_ = new LinkedList<Link>();
    }

    /**
     * Connects this node with the one provided, and assumes that this
     * node is the source and the one given the destination.
     *
     * @param destination The destination node to connect to this one
     * @return A {@link Link}
     * @throws ModelException If the node provided is the same as this one
     */
    public Link connect(Node destination) throws ModelException {
        if (destination == this) {
            throw new ModelException("Vertices cannot be the same");
        }
        String linkName = getName() + "-" + destination.getName();
        Link newLink = new Link(linkName, this, destination);
        links_.add(newLink);
        return newLink;
    }

    List<Link> getLinks() {
        return links_;
    }
}
