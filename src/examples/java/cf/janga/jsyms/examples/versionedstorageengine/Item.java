package cf.janga.jsyms.examples.versionedstorageengine;

import java.util.Optional;
import java.util.UUID;

/**
 * An item that can be written and read.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Item {

    private Optional<Integer> serial_;

    private String id_;

    public Item() {
        serial_ = Optional.empty();
        id_ = UUID.randomUUID().toString();
    }

    Item(String id, int serial) {
        serial_ = Optional.of(serial);
        id_ = id;
    }

    public Item withSerial(int serial) {
        return new Item(id_, serial);
    }

    public Optional<Integer> getSerial() {
        return serial_;
    }

    public String getId() {
        return id_;
    }
}
