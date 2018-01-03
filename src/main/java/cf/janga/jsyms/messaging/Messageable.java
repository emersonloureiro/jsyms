package cf.janga.jsyms.messaging;

/**
 * An entity that can receive messages from another entity.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public interface Messageable {

    /**
     * Sends the message provided to this messageable.
     *
     * @param message Message to be sent
     */
    void doMessage(Message message);
}
