package cf.janga.jsyms.core;

import org.junit.Test;
import org.mockito.Mockito;

public class SimulationTest {

    @Test
    public void shouldRun() {
        CompositeSteppable steppable = Mockito.mock(CompositeSteppable.class);

        int iterations = 3;
        FinishingCondition condition = new MaxIterationsCondition(iterations);
        Simulation simulation = new Simulation("test", "For test", steppable, condition);
        simulation.run();

        // Should've started the steppable
        Mockito.verify(steppable, Mockito.times(1)).start();

        // Should've stopped the steppable
        Mockito.verify(steppable, Mockito.times(1)).stop();

        // Should've ended once condition is true
        Mockito.verify(steppable, Mockito.times(iterations)).step();
    }
}
