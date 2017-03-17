package cf.janga.ds2.sim;

import java.util.Collection;

public class MockCompositeSteppable extends CompositeSteppable {

    public int numberfOfSteps_ = 0;

    public MockCompositeSteppable(Collection<? extends Steppable> steppables) {
        super(steppables);
    }

    @Override
    public void step() {
        // Does nothing
        numberfOfSteps_++;
    }
}

