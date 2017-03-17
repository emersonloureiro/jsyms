package cf.janga.ds2.sim;

/**
 * An {@link Runner} which runs until a condition is satisfied.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class ConditionBasedRunner extends Runner {

    /** Condition which determines when the simulation stops. */
    private ConvergenceCondition condition_;

    /**
     * Creates a new <code>ConditionBasedRunner</code>.
     * @param simulation The simulation to be ran by this {@link Runner}.
     * @param condition Condition which determines when the simulation stops.
     */
    public ConditionBasedRunner(final Simulation simulation, final ConvergenceCondition condition) {
        super(simulation);
        this.condition_ = condition;
    }

    @Override
    protected boolean finished() {
        return this.condition_.isSatisfied(this.simulation());
    }
}
