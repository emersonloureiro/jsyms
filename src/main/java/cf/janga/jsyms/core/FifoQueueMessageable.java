package cf.janga.jsyms.core;

import cf.janga.jsyms.messaging.Message;
import cf.janga.jsyms.messaging.Messageable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation of a Messageable which stores the messages it receives
 * via the doMessage method in a FIFO queue to be processed. At each step, it pops the message at the
 * front of the queue, and passes it to the subclass to be processed via the
 * processMessage method.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 */
public abstract class FifoQueueMessageable implements Messageable, Steppable {

    private final Queue<Message> messageQueue;

    /**
     * creates a new <code>ServiceInstance</code>.
     */
    public FifoQueueMessageable() {
        this.messageQueue = new LinkedList<>();
    }

    @Override
    public void start() {
        this.messageQueue.clear();
    }

    @Override
    public void step() {
        if (this.messageQueue.peek() != null) {
            Message request = this.messageQueue.poll();
            processMessage(request);
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public final void doMessage(Message message) {
        this.messageQueue.add(message);
    }

    /**
     * Performs the processing of the provided message.
     *
     * @param message The message to be processed.
     */
    protected abstract void processMessage(Message message);
}
