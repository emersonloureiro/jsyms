package cf.janga.ds2.ext.backend;

import cf.janga.ds2.sim.Steppable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an instance of some backend service/application.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class ServiceInstance implements Requestable, Steppable {

    private Queue<Request> requestQueue_;

    /**
     * creates a new <code>ServiceInstance</code>.
     */
    public ServiceInstance() {
        requestQueue_ = new LinkedList<>();
    }

    @Override
    public void start() {
        requestQueue_ = new LinkedList<>();
    }

    @Override
    public void step() {
        if (requestQueue_.peek() != null) {
            Request request = requestQueue_.poll();
            processRequest(request);
        }
    }

    protected abstract void processRequest(Request request);

    @Override
    public void stop() {
    }

    public void doRequest(Request request) {
        requestQueue_.add(request);
    }
}
