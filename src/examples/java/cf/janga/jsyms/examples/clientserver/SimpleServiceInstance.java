package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.messaging.Message;
import cf.janga.jsyms.ext.backend.ServiceInstance;

/**
 * Simple service instance for client/service example.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class SimpleServiceInstance extends ServiceInstance {

    @Override
    protected void processMessage(Message message) {
        System.out.println("Processed request " + message.getId());
    }
}
