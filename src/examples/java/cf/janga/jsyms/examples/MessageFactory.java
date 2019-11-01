package cf.janga.jsyms.examples;

import cf.janga.jsyms.messaging.Message;

public interface MessageFactory {

    Message newMessage(Client client);
}
