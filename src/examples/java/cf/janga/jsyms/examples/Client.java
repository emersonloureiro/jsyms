package cf.janga.jsyms.examples;

import cf.janga.jsyms.core.Steppable;
import cf.janga.jsyms.examples.clientserver.Response;
import cf.janga.jsyms.messaging.Message;
import cf.janga.jsyms.messaging.Messageable;

public class Client implements Steppable, Messageable {

    private final float requestChance_;
    private final Messageable messageable;
    private final MessageFactory messageFactory;

    public Client(float requestChance, Messageable messageable, MessageFactory messageFactory) {
        if (requestChance > 1 || requestChance < 0) {
            throw new RuntimeException("Request chance needs to be between 0 and 1");
        }
        requestChance_ = requestChance;
        this.messageable = messageable;
        this.messageFactory = messageFactory;
    }

    @Override
    public void start() {
    }

    @Override
    public void step() {
        if (Math.random() < requestChance_) {
            System.out.println("Client issued request...");
            messageable.doMessage(this.messageFactory.newMessage(this));
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void doMessage(Message message) {
        if (message instanceof Response) {
            System.out.println("Received response...");
        }
    }
}
