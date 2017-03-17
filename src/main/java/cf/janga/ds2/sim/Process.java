package cf.janga.ds2.sim;

import cf.janga.ds2.core.Vertex;

/**
 * An process is a special type of vertex in a graph, being able to be stepped
 * over time.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public abstract class Process extends Vertex implements Steppable {

    /**
     * Creates a new {@code Agent}.
     * @param name Name of this {@code Agent}.
     */
    public Process(final String name) {
        super(name);
    }
}
