package cf.janga.ds2.examples;

import cf.janga.ds2.sim.Steppable;

import java.util.UUID;

public class LoadBalancer implements Steppable {

    private final ServiceInstance instances_[];

    private int currentInstance_;

    private int pendingRequests_;

    public LoadBalancer(ServiceInstance instances[]) {
        instances_ = instances;
    }

    @Override
    public void start() {
        currentInstance_ = 0;
        pendingRequests_ = 0;
    }

    @Override
    public void step() {
        if (pendingRequests_ > 0) {
            if (currentInstance_ == instances_.length) {
                currentInstance_ = 0;
            }
            String request = UUID.randomUUID().toString();
            instances_[currentInstance_].doRequest(request);
            pendingRequests_--;
            currentInstance_++;
            System.out.println("Load balancer forwarded request " + request + ". Currently " + pendingRequests_ + " left to be processed");
        }
    }

    @Override
    public void stop() {
    }

    public void doRequest() {
        pendingRequests_++;
    }
}
