package cf.janga.ds2.model.net;

/**
 * Default basic implementation of a {@link NetworkElement}.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class NetworkElementImpl implements NetworkElement {

    /**
     * Name of this element.
     */
    private final String name_;

    /**
     * Creates a new <code>NetworkElementImpl</code>.
     *
     * @param name Name of this element.
     */
    public NetworkElementImpl(String name) {
        this.name_ = name;
    }

    /**
     * Returns the getName of this element.
     *
     * @return String
     */
    public String getName() {
        return this.name_;
    }
}
