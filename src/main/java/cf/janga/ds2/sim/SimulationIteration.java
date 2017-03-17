package cf.janga.ds2.sim;

/**
 * Represents a single iteration of a simulation (i.e., after a round
 * of stepping the steppables in the simulation)
 */
public class SimulationIteration {

    private final int iteration_;

    public SimulationIteration(int iteration) {
        iteration_ = iteration;
    }

    public int getIteration_() {
        return iteration_;
    }
}
