package cf.janga.ds2.examples;

import cf.janga.ds2.ext.backend.LoadBalancer;
import cf.janga.ds2.ext.backend.ServiceInstance;
import cf.janga.ds2.core.*;

import java.util.LinkedList;
import java.util.List;

public class ClientServerExample {

    public static void main(String args[]) {
        ServiceInstance instances[] = new ServiceInstance[3];
        instances[0] = new SimpleServiceInstance();
        instances[1] = new SimpleServiceInstance();
        instances[2] = new SimpleServiceInstance();
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
