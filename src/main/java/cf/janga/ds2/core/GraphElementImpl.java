package cf.janga.ds2.core;

/**
 * Default basic implementation of a GraphElement.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class GraphElementImpl implements GraphElement {

    /** Name of this element. */
    private String name_;

    /**
     * Creates a new <code>GraphElementImpl</code>.
     * @param name Name of this element.
     */
    public GraphElementImpl(final String name) {
        this.name_ = name;
    }

    /**
     * Returns the getName of this element.
     * @return String
     */
    public final String getName() {
        return this.name_;
    }
}
