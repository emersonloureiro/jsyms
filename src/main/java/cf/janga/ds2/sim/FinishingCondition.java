package cf.janga.ds2.sim;

/**
 * A finishing condition for a simulation.
 */
public interface FinishingCondition {

    /**
     * Checks whether the condition is satisfied or not.
     *
     * @return Returns true if the condition is satisfied and false otherwise.
     */
    boolean isSatisfied(SimulationIteration iteration);
}
