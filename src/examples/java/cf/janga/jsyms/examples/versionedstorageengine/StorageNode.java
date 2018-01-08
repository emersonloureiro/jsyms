package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.messaging.Message;

import java.util.HashMap;

/**
 * The nodes that persists data to disk.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class StorageNode extends FifoQueueMessageable {

    private HashMap<String, Item> persistentStore_;

    private int nextSerial_;

    public StorageNode() {
        persistentStore_ = new HashMap<>(10);
        nextSerial_ = 1;
    }

    @Override
    protected void processMessage(Message message) {
        if (message instanceof WriteMessage) {
            Message writeResponse = write(((WriteMessage) message));
            message.getSource().doMessage(writeResponse);
            System.out.println("Processed write request " + message.getId() + " for item " + ((WriteMessage) message).getItem().getId());
        } else {
            throw new RuntimeException("Unknown request type " + message);
        }
    }

    private Message write(WriteMessage writeRequest) {
        Item item = writeRequest.getItem();
        if (!item.getSerial().isPresent()) {
            // Insert
            persistentStore_.put(item.getId(), item.withSerial(nextSerial_));
            nextSerial_ += 1;
            return new SNWriteSucceeded(this, writeRequest.getId(), item);
        } else {
            // Update
            if (persistentStore_.containsKey(item.getId())) {
                Item persistedItem = persistentStore_.get(item.getId());
                int currentItemSerial = persistedItem.getSerial().get();
                int newItemSerial = item.getSerial().get();
                if (newItemSerial < currentItemSerial) {
                    return new SNWriteFailed(this, writeRequest.getId(), item, "New item with id " + item.getId() + " has lower serial " + newItemSerial + " compared to existing item serial " + currentItemSerial);
                } else {
                    persistentStore_.put(item.getId(), item);
                    return new SNWriteSucceeded(this, writeRequest.getId(), item);
                }
            } else {
                return new SNWriteFailed(this, writeRequest.getId(), item, "Item with id " + item.getId() + " does not exist");
            }
        }
    }
}
