package cf.janga.jsyms.examples.cells;

import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.examples.clientserver.LoadBalancer;
import cf.janga.jsyms.messaging.Message;

import java.util.List;
import java.util.Random;

public class Cell extends FifoQueueMessageable {

    private final List<LoadBalancer> loadBalancers;

    private final Random random;

    public Cell(List<LoadBalancer> loadBalancers) {
        this.loadBalancers = loadBalancers;
        this.random = new Random();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void processMessage(Message message) {
        if (message instanceof RouterRequestMessage) {
            int loadBalancerNumber = this.random.nextInt(this.loadBalancers.size());
            LoadBalancer loadBalancer = this.loadBalancers.get(loadBalancerNumber);
            System.out.println(String.format("Forwarding request to load balancer %s", loadBalancerNumber));
            loadBalancer.doMessage(message);
        }
    }
}
