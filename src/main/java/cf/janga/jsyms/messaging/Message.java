package cf.janga.jsyms.messaging;

/**
 * A message that can be exchanged between two parties.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public interface Message {

    /**
     * Returns the ID of this message.
     *
     * @return String
     */
    String getId();

    /**
     * Returns the entity that sent this message.
     *
     * @return a Messageable
     */
    Messageable getSource();
}
