package cf.janga.jsyms.core;

/**
 * An {@link FinishingCondition} which is based on the number of iterations during which the
 * simulation should be ran.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class MaxIterationsCondition implements FinishingCondition {

    private final int numberOfIterations;

    /**
     * Creates a new <code>MaxIterationsCondition</code>.
     *
     * @param numberOfIterations Number of iterations for which to run the
     *                           simulation.
     */
    public MaxIterationsCondition(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public boolean isSatisfied(SimulationIteration iteration) {
        return iteration.getIteration() > this.numberOfIterations;
    }
}
