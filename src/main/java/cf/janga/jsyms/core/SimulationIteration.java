package cf.janga.jsyms.core;

/**
 * Represents a single iteration of a simulation (i.e., after a round
 * of stepping the steppables in the simulation)
 */
public class SimulationIteration {

    private final int iteration;

    public SimulationIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return this.iteration;
    }
}
