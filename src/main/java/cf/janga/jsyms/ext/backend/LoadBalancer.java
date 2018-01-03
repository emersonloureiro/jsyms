package cf.janga.jsyms.ext.backend;

import cf.janga.jsyms.core.Steppable;
import cf.janga.jsyms.messaging.Message;
import cf.janga.jsyms.messaging.Messageable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A simple load balancer in a typical backend software stack. It redirects requests
 * to a number of instances on a round robin basis.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class LoadBalancer implements Messageable, Steppable {

    private final Messageable instances_[];

    private int currentInstance_;

    private Queue<Message> requestQueue_;

    /**
     * Creates a new <code>LoadBalancer</code>
     *
     * @param instances The instances behind the load balancer.
     */
    public LoadBalancer(Messageable instances[]) {
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
            Message request = requestQueue_.poll();
            instances_[currentInstance_].doMessage(request);
            currentInstance_++;
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void doMessage(Message message) {
        requestQueue_.add(message);
    }
}
