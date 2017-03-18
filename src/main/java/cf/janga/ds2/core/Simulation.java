package cf.janga.ds2.core;

/**
 * Holds all the information concerning a particular simulation.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class Simulation {

    private final String description_;

    private final String name_;

    private final CompositeSteppable steppable_;

    private final FinishingCondition condition_;

    private boolean stopRequested_;

    /**
     * Creates a new {@code Simulation} with the provided getName and getDescription.
     *
     * @param name        Name of this simulation.
     * @param description Description of this simulation.
     * @param steppable   Responsible for stepping all elements of the simulation
     */
    public Simulation(String name, String description, CompositeSteppable steppable, FinishingCondition condition) {
        name_ = name;
        description_ = description;
        steppable_ = steppable;
        condition_ = condition;
        stopRequested_ = false;
    }

    /**
     * Returns the name of this simulation.
     *
     * @return String
     */
    public final String getName() {
        return name_;
    }

    /**
     * Returns the description of this simulation.
     *
     * @return String
     */
    public final String getDescription() {
        return description_;
    }

    public final void run() {
        // Initializes the steppable of the simulation.
        steppable_.start();
        int iteration = 1;

        while (!condition_.isSatisfied(new SimulationIteration(iteration)) && !stopRequested_) {
            // ... and steps the steppable of the simulation.
            steppable_.step();
            iteration++;
        }

        // Signals the steppable of the simulation to stop or stop stepping its
        // internal steppables, if that's the case.
        steppable_.stop();
        stopRequested_ = false;
    }

    /**
     * Requests the simulation to be stopped.
     */
    public final void stop() {
        stopRequested_ = true;
    }
}
