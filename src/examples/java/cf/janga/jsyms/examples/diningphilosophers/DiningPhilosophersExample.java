package cf.janga.jsyms.examples.diningphilosophers;

import cf.janga.jsyms.core.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Main class for the dining philosophers example.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class DiningPhilosophersExample {

    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();
        Philosopher philosopher1 = new Philosopher(1, fork1, fork2);
        Philosopher philosopher2 = new Philosopher(2, fork2, fork3);
        Philosopher philosopher3 = new Philosopher(3, fork3, fork4);
        Philosopher philosopher4 = new Philosopher(4, fork4, fork5);
        Philosopher philosopher5 = new Philosopher(5, fork5, fork1);

        List<Steppable> steppables = new LinkedList<>();
        steppables.add(philosopher1);
        steppables.add(philosopher2);
        steppables.add(philosopher3);
        steppables.add(philosopher4);
        steppables.add(philosopher5);
        CompositeSteppable steppable = new ShufflingCompositeSteppable(steppables);

        Simulation simulation = new Simulation("Dining philosophers", "", steppable, new MaxIterationsCondition(100));
        simulation.run();
    }
}
