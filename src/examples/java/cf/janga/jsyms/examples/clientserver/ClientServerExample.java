package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.ext.backend.LoadBalancer;
import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.core.*;

import java.util.LinkedList;
import java.util.List;

public class ClientServerExample {

    public static void main(String args[]) {
        FifoQueueMessageable instances[] = new FifoQueueMessageable[3];
        instances[0] = new SimpleFifoQueueMessageable();
        instances[1] = new SimpleFifoQueueMessageable();
        instances[2] = new SimpleFifoQueueMessageable();
        LoadBalancer loadBalancer = new LoadBalancer(instances);
        Client client = new Client(0.5f, loadBalancer);

        List<Steppable> steppables = new LinkedList<>();
        steppables.add(client);
        steppables.add(loadBalancer);
        steppables.add(instances[0]);
        steppables.add(instances[1]);
        steppables.add(instances[2]);
        CompositeSteppable steppable = new ShufflingCompositeSteppable(steppables);

        Simulation simulation = new Simulation("Client server", "Simple client/server simulation", steppable, new MaxIterationsCondition(100));
        simulation.run();
    }
}
