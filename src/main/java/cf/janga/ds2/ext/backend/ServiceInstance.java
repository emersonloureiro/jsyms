package cf.janga.ds2.ext.backend;

import cf.janga.jsyms.core.Steppable;
import cf.janga.ds2.messaging.Message;
import cf.janga.ds2.messaging.Messageable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an instance of some backend service/application.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class ServiceInstance implements Messageable, Steppable {

    private final Queue<Message> messageQueue_;

    /**
     * creates a new <code>ServiceInstance</code>.
     */
    public ServiceInstance() {
        messageQueue_ = new LinkedList<>();
    }

    @Override
    public void start() {
        messageQueue_.clear();
    }

    @Override
    public void step() {
        if (messageQueue_.peek() != null) {
            Message request = messageQueue_.poll();
            processMessage(request);
        }
    }

    @Override
    public void stop() {
    }

    /**
     * Performs the processing of the provided message.
     *
     * @param message The message to be processed.
     */
    protected abstract void processMessage(Message message);

    /**
     * Requests this service instance to process the provided
     * message.
     *
     * @param message Message to be processed
     */
    public final void doMessage(Message message) {
        messageQueue_.add(message);
    }
}
