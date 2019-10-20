package cf.janga.jsyms.core;

/**
 * A finishing condition for a simulation.
 */
public interface FinishingCondition {

    /**
     * Checks whether the condition is satisfied or not.
     *
     * @return Returns true if the condition is satisfied and false otherwise.
     *
     * @param iteration the current iteration of the simulation.
     */
    boolean isSatisfied(SimulationIteration iteration);
}
