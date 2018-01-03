package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.core.Steppable;
import cf.janga.ds2.ext.backend.LoadBalancer;
import cf.janga.ds2.messaging.BaseMessage;
import cf.janga.ds2.messaging.Message;
import cf.janga.ds2.messaging.Messageable;

public class Client implements Steppable, Messageable {

    private final float requestChance_;
    private final LoadBalancer loadBalancer_;

    public Client(float requestChance, LoadBalancer loadBalancer) {
        if (requestChance > 1) {
            throw new RuntimeException("Request chance needs to be between 0 and 1");
        }
        requestChance_ = requestChance;
        loadBalancer_ = loadBalancer;
    }

    @Override
    public void start() {
    }

    @Override
    public void step() {
        if (Math.random() < requestChance_) {
            System.out.println("Client issued request...");
            loadBalancer_.doMessage(new BaseMessage(this));
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void doMessage(Message message) {
    }
}
