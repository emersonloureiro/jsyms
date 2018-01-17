package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.messaging.BaseMessage;
import cf.janga.jsyms.messaging.Messageable;

/**
 * A simple class to represent a request sent from the client.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Request extends BaseMessage {

    /**
     * Creates a new object of this class with the
     * given source as the entity that's sending
     * it.
     *
     * @param source a Messageable
     */
    public Request(Messageable source) {
        super(source);
    }
}
