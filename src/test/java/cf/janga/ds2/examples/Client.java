package cf.janga.ds2.examples;

import cf.janga.ds2.sim.Steppable;

public class Client implements Steppable {

    private final float requestChance_;
    private final LoadBalancer loadBalancer_;

    public Client(float requestChance, LoadBalancer loadBalancer) {
        if (requestChance > 1) {
            throw new RuntimeException("Request chance needs to be between 0 and 1");
        }
        requestChance_ = requestChance;
        loadBalancer_ = loadBalancer;
    }

    @Override
    public void start() {
    }

    @Override
    public void step() {
        if (Math.random() < requestChance_) {
            System.out.println("Client issued request...");
            loadBalancer_.doRequest();
        }
    }

    @Override
    public void stop() {
    }
}
