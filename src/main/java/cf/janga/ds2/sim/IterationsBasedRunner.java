package cf.janga.ds2.sim;

/**
 * An {@link Runner} which is based on the number of iterations during which the
 * simulation should be ran.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class IterationsBasedRunner extends Runner {

    /**
     * Number of iterations for which to run the simulation.
     */
    private int numberOfIterations_;
    /**
     * The current iteration of the simulation.
     */
    private int currentIteration_;

    /**
     * Creates a new <code>IterationsBasedRunner</code>.
     *
     * @param simulation         The simulation to be ran by this {@link Runner}.
     * @param numberOfIterations Number of iterations for which to run the
     *                           simulation.
     */
    public IterationsBasedRunner(final Simulation simulation, final int numberOfIterations) {
        super(simulation);
        this.numberOfIterations_ = numberOfIterations;
        this.currentIteration_ = 1;
    }

    @Override
    protected boolean finished() {
        boolean finished = false;

        if (this.currentIteration_ > this.numberOfIterations_) {
            finished = true;
            this.currentIteration_ = 1;
        } else {
            this.currentIteration_++;
        }

        return finished;
    }
}
