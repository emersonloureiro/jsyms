package cf.janga.ds2.sim;

import java.util.Collection;
import java.util.Iterator;

/**
 * A <code>Selector</code> that selects steppables sequentially from a list.
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class SequentialCompositeSteppable extends CompositeSteppable {

    /**
     * Creates and initializes a new <code>SequentialCompositeSteppable</code>.
     * @param steppables Collection of steppables to be selected by this
     *                selector.
     */
    public SequentialCompositeSteppable(final Collection<? extends Steppable> steppables) {
        super(steppables);
    }

    @Override
    public void step() {
        for (Iterator<? extends Steppable> steppablesIterator = this.steppables().iterator(); steppablesIterator.hasNext();) {
            Steppable steppable = steppablesIterator.next();
            steppable.step();
        }
    }
}
