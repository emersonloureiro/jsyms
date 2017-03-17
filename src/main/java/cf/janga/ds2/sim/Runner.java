package cf.janga.ds2.sim;

/**
 * An <code>Runner</code> is responsible for running a simulation. It controls
 * the steps involved to initialize and keep a simulation running, also deciding
 * when it should be finished.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class Runner {

    /**
     * Indicates if a stop has been requested on this runner.
     */
    private boolean stopRequested_;

    /**
     * The simulation to be ran by this {@link Runner}.
     */
    private Simulation simulation_;

    /**
     * The steppable of the simulation to be run by this runner.
     */
    private Steppable steppable_;

    /**
     * Creates a new <code>RunnerImpl</code>.
     *
     * @param simulation The simulation to be ran by this {@link Runner}.
     */
    public Runner(final Simulation simulation) {
        this.simulation_ = simulation;
        this.stopRequested_ = false;
    }

    /**
     * Starts the simulation controlled by this <code>Runner</code>.
     */
    public final void start() {
        // Initializes the simulation before doing anything else...
        this.steppable_ = this.simulation_.init();

        // Initializes the steppable of the simulation.
        this.steppable_.start();

        while (!this.finished() && !this.stopRequested_) {
            // ... and steps the steppable of the simulation.
            this.steppable_.step();
        }

        // Signals the steppable of the simulation to stop or stop stepping its
        // internal steppables, if that's the case.
        this.steppable_.stop();
        this.stopRequested_ = false;
    }

    /**
     * Stops the simulation controlled by this <code>Runner</code>.
     */
    public final void stop() {
        this.stopRequested_ = true;
    }

    /**
     * Returns the simulation to be ran by this {@link Runner}.
     *
     * @return A {@link Simulation} object.
     */
    final Simulation simulation() {
        return this.simulation_;
    }

    /**
     * Determines if this runner is finished running the simulation. This method
     * is invoked at the <b>start</b> of each iteration.
     *
     * @return True if the runner is finished and false otherwise.
     */
    abstract boolean finished();
}
