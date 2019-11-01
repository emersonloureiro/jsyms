package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.examples.Client;
import cf.janga.jsyms.examples.MessageFactory;
import cf.janga.jsyms.messaging.Message;

public class RequestMessageFactory implements MessageFactory {
    @Override
    public Message newMessage(Client client) {
        return new Request(client);
    }
}
