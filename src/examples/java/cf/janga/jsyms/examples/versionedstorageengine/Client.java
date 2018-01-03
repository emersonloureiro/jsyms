package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.jsyms.core.Steppable;
import cf.janga.ds2.messaging.Message;
import cf.janga.ds2.messaging.Messageable;

/**
 * A client that can issue reads and writes to a
 * replication group.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Client implements Steppable, Messageable {

    private final ReplicaGroup replicaGroup_;

    public Client(ReplicaGroup replicaGroup) {
        replicaGroup_ = replicaGroup;
    }

    @Override
    public void step() {
        if (Math.random() < 0.5) {
            // Reading
        } else {
            if (Math.random() < 0.5) {
                // Inserting
                Item newItem = new Item();
                WriteMessage request = new WriteMessage(this, newItem);
                replicaGroup_.doMessage(request);
                System.out.println("Sent write with id " + request.getId() + " to replica group");
            } else {
                // Updating
            }
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void doMessage(Message message) {
    }
}
