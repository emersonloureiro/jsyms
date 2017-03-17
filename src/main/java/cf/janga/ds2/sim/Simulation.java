package cf.janga.ds2.sim;

import java.awt.*;

/**
 * Holds all the information concerning a particular simulation.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class Simulation {

    /**
     * Holds the description of this simulation.
     */
    private String description_;

    /**
     * Holds the name of this simulation.
     */
    private String name_;

    /**
     * Creates a new {@code Simulation} with the provided getName and getDescription.
     *
     * @param name        Name of this simulation.
     * @param description Description of this simulation.
     */
    public Simulation(final String name, final String description) {
        this.name_ = name;
        this.description_ = description;
    }

    /**
     * Returns the name of this simulation.
     *
     * @return String
     */
    public final String getName() {
        return this.name_;
    }

    /**
     * Returns the description of this simulation.
     *
     * @return String
     */
    public final String getDescription() {
        return this.description_;
    }

    /**
     * Initializes this simulation.
     *
     * @return Steppable The object that is steppable and will be responsible
     * for stepping the steppables of this simulation.
     */
    public abstract CompositeSteppable init();
}
