package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.ds2.messaging.BaseMessage;
import cf.janga.ds2.messaging.Messageable;

/**
 * ADD COMMENT
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class ItemMessage extends BaseMessage {

    private final Item originalItem_;

    public ItemMessage(Messageable source, Item originalItem) {
        super(source);
        originalItem_ = originalItem;
    }

    public Item getOriginalItem() {
        return originalItem_;
    }
}
