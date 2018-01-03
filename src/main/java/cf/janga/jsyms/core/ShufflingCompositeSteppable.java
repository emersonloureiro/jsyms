package cf.janga.jsyms.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A {@link CompositeSteppable} which randomly shuffles its steppables and sequentially
 * steps each of them.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class ShufflingCompositeSteppable extends CompositeSteppable {

    /**
     * Creates and initializes a new <code>ShufflingCompositeSteppable</code>.
     *
     * @param steppables Collection of steppables to be stepped
     */
    public ShufflingCompositeSteppable(Collection<? extends Steppable> steppables) {
        super(steppables);
    }

    @Override
    public void step() {
        List<Steppable> shuffledSteppables = new ArrayList<>(steppables());
        Collections.shuffle(shuffledSteppables);
        for (Steppable steppable : shuffledSteppables) {
            steppable.step();
        }
    }
}
