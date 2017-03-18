package cf.janga.ds2.ext.backend;

import cf.janga.ds2.core.Steppable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A simple load balancer in a typical backend software stack. It redirects requests
 * to a number of instances on a round robin basis.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class LoadBalancer implements Requestable, Steppable {

    private final Requestable instances_[];

    private int currentInstance_;

    private Queue<Request> requestQueue_;

    /**
     * Creates a new <code>LoadBalancer</code>
     *
     * @param instances The instances behind the load balancer.
     */
    public LoadBalancer(Requestable instances[]) {
        instances_ = instances;
    }

    @Override
    public void start() {
        currentInstance_ = 0;
        requestQueue_ = new LinkedList<>();
    }

    @Override
    public void step() {
        if (requestQueue_.peek() != null) {
            if (currentInstance_ == instances_.length) {
                currentInstance_ = 0;
            }
            Request request = requestQueue_.poll();
            instances_[currentInstance_].doRequest(request);
            currentInstance_++;
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void doRequest(Request request) {
        requestQueue_.add(request);
    }
}
