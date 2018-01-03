package cf.janga.jsyms.messaging;

import java.util.UUID;

/**
 * Utility base class for messages.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class BaseMessage implements Message {

    private final String id_;

    private final Messageable source_;

    /**
     * Creates a new object of this class with the
     * given source as the entity that's sending
     * it.
     *
     * @param source a Messageable
     */
    public BaseMessage(Messageable source) {
        id_ = UUID.randomUUID().toString();
        source_ = source;
    }

    @Override
    public String getId() {
        return id_;
    }

    @Override
    public Messageable getSource() {
        return source_;
    }
}
