package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.core.CompositeSteppable;
import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.core.MaxIterationsCondition;
import cf.janga.jsyms.core.ShufflingCompositeSteppable;
import cf.janga.jsyms.core.Simulation;
import cf.janga.jsyms.core.Steppable;
import cf.janga.jsyms.examples.Client;

import java.util.LinkedList;
import java.util.List;

public class ClientServerExample {

    public static void main(String args[]) {
        FifoQueueMessageable instances[] = new FifoQueueMessageable[3];
        instances[0] = new ServiceInstance();
        instances[1] = new ServiceInstance();
        instances[2] = new ServiceInstance();
        LoadBalancer loadBalancer = new LoadBalancer(instances);
        Client client = new Client(0.5f, loadBalancer, new RequestMessageFactory());

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
