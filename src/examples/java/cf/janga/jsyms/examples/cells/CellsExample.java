package cf.janga.jsyms.examples.cells;

import cf.janga.jsyms.core.CompositeSteppable;
import cf.janga.jsyms.core.MaxIterationsCondition;
import cf.janga.jsyms.core.ShufflingCompositeSteppable;
import cf.janga.jsyms.core.Simulation;
import cf.janga.jsyms.core.Steppable;
import cf.janga.jsyms.examples.Client;
import cf.janga.jsyms.examples.clientserver.LoadBalancer;
import cf.janga.jsyms.examples.clientserver.ServiceInstance;
import cf.janga.jsyms.messaging.Messageable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CellsExample {

    public static void main(String[] args) {
        List<Steppable> steppables = new LinkedList<>();
        LoadBalancer cell1Lb1 = createLB(5, steppables);
        LoadBalancer cell1Lb2 = createLB(6, steppables);
        LoadBalancer cell1Lb3 = createLB(10, steppables);
        LoadBalancer cell2Lb1 = createLB(5, steppables);
        LoadBalancer cell2Lb2 = createLB(6, steppables);
        LoadBalancer cell2Lb3 = createLB(10, steppables);
        LoadBalancer cell3Lb1 = createLB(5, steppables);
        LoadBalancer cell3Lb2 = createLB(6, steppables);
        LoadBalancer cell3Lb3 = createLB(10, steppables);
        Cell cell1 = new Cell(Arrays.asList(cell1Lb1, cell1Lb2, cell1Lb3));
        Cell cell2 = new Cell(Arrays.asList(cell2Lb1, cell2Lb2, cell2Lb3));
        Cell cell3 = new Cell(Arrays.asList(cell3Lb1, cell3Lb2, cell3Lb3));
        RequestRouter requestRouter = new RequestRouter(Arrays.asList(cell1, cell2, cell3));
        Client client = new Client(0.5f, requestRouter, new RouterRequestMessageFactory());

        steppables.add(requestRouter);
        steppables.add(client);
        steppables.add(cell1Lb1);
        steppables.add(cell1Lb2);
        steppables.add(cell1Lb3);
        steppables.add(cell2Lb1);
        steppables.add(cell2Lb2);
        steppables.add(cell2Lb3);
        steppables.add(cell3Lb1);
        steppables.add(cell3Lb2);
        steppables.add(cell3Lb3);
        steppables.add(cell1);
        steppables.add(cell2);
        steppables.add(cell3);

        CompositeSteppable steppable = new ShufflingCompositeSteppable(steppables);
        Simulation cellsSimulation = new Simulation("Cells simulation", "Cells simulation", steppable, new MaxIterationsCondition(200));
        cellsSimulation.run();
    }

    private static LoadBalancer createLB(int numberOfInstances, List<Steppable> steppables) {
        Messageable[] instances = new Messageable[numberOfInstances];
        for (int i = 0; i < instances.length; i++) {
            ServiceInstance serviceInstance = new ServiceInstance();
            instances[i] = serviceInstance;
            steppables.add(serviceInstance);
        }
        return new LoadBalancer(instances);
    }
}
