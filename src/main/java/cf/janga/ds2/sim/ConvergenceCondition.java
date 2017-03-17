package cf.janga.ds2.sim;

/**
 * A condition determines when a {@link Runner} should stop running.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public interface ConvergenceCondition {

    /**
     * Indicates whether this condition is satisfied or not.
     * @param simulation The simulation under which the condition will checked.
     * @return True if the condition is satisfied and false otherwise.
     */
    boolean isSatisfied(final Simulation simulation);
}
