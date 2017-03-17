package cf.janga.ds2.sim;

import java.util.Collection;
import java.util.Random;
import java.util.Vector;

/**
 * A <code>Selector</code> that selects steppables in a random way.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public final class RandomCompositeSteppable extends CompositeSteppable {

    /**
     * Creates and initializes a new <code>RandomSelector</code>.
     *
     * @param steppables Collection of steppables to be selected by this
     *                   selector.
     */
    public RandomCompositeSteppable(Collection<Steppable> steppables) {
        super(steppables);
    }

    @Override
    public void step() {
        Vector<Steppable> steppablesListClone = new Vector<Steppable>(this.steppables());
        Random random = new Random();

        for (int i = 0; i < this.steppables().size(); i++) {
            // Returns a random integer between 0 and the size of the steppables
            // list clone - 1.
            int randIndex = random.nextInt(steppablesListClone.size());
            // Removes the steppable located at the random index generated from
            // the list and returns it...
            Steppable randomSteppable = steppablesListClone.remove(randIndex);
            // ...and steps it.
            randomSteppable.step();
        }
    }
}
