package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.messaging.Message;

/**
 * Simple service instance for client/service example.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class ServiceInstance extends FifoQueueMessageable {

    @Override
    protected void processMessage(Message message) {
        System.out.println("Processed request " + message.getId());
        message.getSource().doMessage(new Response(this, message.getSource()));
    }
}
