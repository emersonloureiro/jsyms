package cf.janga.ds2.core;

import java.util.Collection;
import java.util.Iterator;

/**
 * A {@link CompositeSteppable} which steps each steppable in the same order
 * every time.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class SequentialCompositeSteppable extends CompositeSteppable {

    /**
     * Creates and initializes a new <code>SequentialCompositeSteppable</code>.
     *
     * @param steppables Collection of steppables to be stepped
     */
    public SequentialCompositeSteppable(Collection<? extends Steppable> steppables) {
        super(steppables);
    }

    @Override
    public void step() {
        for (Iterator<? extends Steppable> steppablesIterator = steppables().iterator(); steppablesIterator.hasNext(); ) {
            Steppable steppable = steppablesIterator.next();
            steppable.step();
        }
    }
}
