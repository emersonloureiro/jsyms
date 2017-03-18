package cf.janga.ds2.examples;

import cf.janga.ds2.sim.Steppable;

import java.util.LinkedList;
import java.util.Queue;

public class ServiceInstance implements Steppable {

    private Queue<String> requestQueue_;

    public ServiceInstance() {
    }

    @Override
    public void start() {
        requestQueue_ = new LinkedList<>();
    }

    @Override
    public void step() {
        if (requestQueue_.peek() != null) {
            String request = requestQueue_.poll();
            System.out.println("Processed request " + request + ".");
        } else {
            System.out.println("Nothing to process on the service instance right now...");
        }
    }

    @Override
    public void stop() {
    }

    public void doRequest(String request) {
        requestQueue_.add(request);
    }
}
