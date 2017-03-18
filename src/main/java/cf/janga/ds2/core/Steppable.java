package cf.janga.ds2.core;

/**
 * Anything that can be stepped, that is, receive step events frequently, when
 * it is then able to do something.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public interface Steppable {

    /**
     * Signals this steppable to initialize itself as the simulation is about to
     * be started.
     */
    void start();

    /**
     * Steps this steppable.
     */
    void step();

    /**
     * Signals this steppable to stop.
     */
    void stop();
}
