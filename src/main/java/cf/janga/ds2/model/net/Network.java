package cf.janga.ds2.model.net;

import java.util.List;

/**
 * A composite element representing a network, with nodes connected to each other, which in
 * turn models the topology of a distributed system.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Network extends NetworkElementImpl {

    private final List<Node> nodes_;

    /**
     * Creates a new <code>Network</code>.
     *
     * @param name Name of this {@code Network}.
     */
    public Network(String name, List<Node> nodes) {
        super(name);
        nodes_ = nodes;
    }

    /**
     * Returns the list of nodes of this network.
     *
     * @return <code>List</code> of <code>Vertex</code> objects
     */
    public List<Node> getNodes() {
        return nodes_;
    }
}
