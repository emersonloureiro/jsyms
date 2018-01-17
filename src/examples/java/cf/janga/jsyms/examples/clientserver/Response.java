package cf.janga.jsyms.examples.clientserver;

import cf.janga.jsyms.messaging.BaseMessage;
import cf.janga.jsyms.messaging.Messageable;

/**
 * A simple class to represent a response sent from the service
 * instance.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Response extends BaseMessage {

    private final Messageable client_;

    /**
     * Creates a new object of this class with the
     * given source as the entity that's sending
     * it.
     *
     * @param source a Messageable
     * @param client the Messageable that originated the request
     */
    public Response(Messageable source, Messageable client) {
        super(source);
        client_ = client;
    }

    public Messageable getClient() {
        return client_;
    }
}
