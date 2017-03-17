package cf.janga.ds2.sim;

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
        Mockito.verify(steppable).start();

        // Should've stopped the steppable
        Mockito.verify(steppable).stop();

        // Should've ended when condition once true
        Mockito.verify(steppable, Mockito.times(iterations)).step();
    }
}
