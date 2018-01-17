package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.examples.clientserver.Request;
import cf.janga.jsyms.examples.clientserver.Response;
import cf.janga.jsyms.messaging.Message;
import cf.janga.jsyms.messaging.Messageable;

/**
 * A simple load balancer in a typical backend software stack. It redirects requests
 * to a number of instances on a round robin basis.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class LoadBalancer extends FifoQueueMessageable {

    private final Messageable instances_[];

    private int currentInstance_;

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
    }

    @Override
    public void stop() {
    }

    @Override
    protected void processMessage(Message message) {
        if (currentInstance_ == instances_.length) {
            currentInstance_ = 0;
        }
        if (message instanceof Request) {
            instances_[currentInstance_].doMessage(message);
            currentInstance_++;
        } else if (message instanceof Response) {
            Response response = (Response) message;
            response.getClient().doMessage(new Response(this, response.getClient()));
        }
    }
}
