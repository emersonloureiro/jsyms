package cf.janga.jsyms.core;

/**
 * Holds all the information concerning a particular simulation.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public class Simulation {

    private final String description;

    private final String name;

    private final CompositeSteppable steppable;

    private final FinishingCondition condition;

    private volatile boolean stopRequested;

    /**
     * Creates a new {@code Simulation} with the provided getName and getDescription.
     *
     * @param name        Name of this simulation.
     * @param description Description of this simulation.
     * @param steppable   Responsible for stepping all elements of the simulation
     * @param condition   The condition determining when the simulation is finished (e.g., after a max number of iterations)
     */
    public Simulation(String name, String description, CompositeSteppable steppable, FinishingCondition condition) {
        this.name = name;
        this.description = description;
        this.steppable = steppable;
        this.condition = condition;
        this.stopRequested = false;
    }

    /**
     * Returns the name of this simulation.
     *
     * @return String
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns the description of this simulation.
     *
     * @return String
     */
    public final String getDescription() {
        return this.description;
    }

    public final void run() {
        // Initializes the steppable of the simulation.
        this.steppable.start();
        int iteration = 1;

        while (!condition.isSatisfied(new SimulationIteration(iteration)) && !this.stopRequested) {
            // ... and steps the steppable of the simulation.
            this.steppable.step();
            iteration++;
        }

        // Signals the steppable of the simulation to stop or stop stepping its
        // internal steppables, if that's the case.
        this.steppable.stop();
        this.stopRequested = false;
    }

    /**
     * Requests the simulation to be stopped.
     */
    public final void stop() {
        this.stopRequested = true;
    }
}
