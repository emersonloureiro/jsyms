package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.jsyms.messaging.BaseMessage;
import cf.janga.jsyms.messaging.Messageable;

/**
 * A message for writing items to the persistent store.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class WriteMessage extends BaseMessage {

    private final Item item;

    public WriteMessage(Messageable requester, Item item) {
        super(requester);
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }
}
