package cf.janga.ds2.core;

import java.util.*;

/**
 * A steppable that is composed of other steppables.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class CompositeSteppable implements Steppable {

    /** Collection of steppables to be selected by this selector. */
    private final List<Steppable> steppables_;

    /**
     * Creates and initializes a new <code>CompositeSteppable</code>.
     * @param steppables Collection of steppables to be selected by this
     *                selector.
     */
    public CompositeSteppable(Collection<? extends Steppable> steppables) {
        steppables_ = new LinkedList<Steppable>(steppables);
    }

    /**
     * Returns the collection of steppables to be selected by this selector.
     * @return A collection of Steppable objects.
     */
    protected Collection<Steppable> steppables() {
        return steppables_;
    }

    @Override
    public void start() {
        // ... Iterate over all steppables...
        for (Iterator<? extends Steppable> steppables = steppables_.iterator(); steppables.hasNext();) {
            // ... and starts each of them.
            steppables.next().start();
        }
    }

    @Override
    public void stop() {
        // ... Iterate over all steppables...
        for (Iterator<? extends Steppable> steppablesIterator = steppables_.iterator(); steppablesIterator.hasNext();) {
            // ...stopping each of them.
            steppablesIterator.next().stop();
        }
    }
}
