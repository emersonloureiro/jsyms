package cf.janga.jsyms.core;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

public class CompositeSteppableTest {

    @Test
    public void shouldStartAllSteppables() {
        Steppable steppable1 = Mockito.mock(Steppable.class);
        Steppable steppable2 = Mockito.mock(Steppable.class);
        Steppable steppable3 = Mockito.mock(Steppable.class);
        List<Steppable> steppables = new LinkedList<>();
        steppables.add(steppable1);
        steppables.add(steppable2);
        steppables.add(steppable3);

        CompositeSteppable steppable = new MockCompositeSteppable(steppables);
        steppable.start();

        for (Steppable aSteppable : steppables) {
            Mockito.verify(aSteppable).start();
        }
    }

    @Test
    public void shouldStopAllSteppables() {
        Steppable steppable1 = Mockito.mock(Steppable.class);
        Steppable steppable2 = Mockito.mock(Steppable.class);
        Steppable steppable3 = Mockito.mock(Steppable.class);
        List<Steppable> steppables = new LinkedList<>();
        steppables.add(steppable1);
        steppables.add(steppable2);
        steppables.add(steppable3);

        CompositeSteppable steppable = new MockCompositeSteppable(steppables);
        steppable.stop();

        for (Steppable aSteppable : steppables) {
            Mockito.verify(aSteppable).stop();
        }
    }
}
