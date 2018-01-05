package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.jsyms.core.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Example of a quorum-based storage service
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class VersionedStorageServiceExample {

    public static void main(String[] args) {
        List<StorageNode> storageNodes = new LinkedList<>();
        StorageNode storageNode1 = new StorageNode();
        storageNodes.add(storageNode1);
        StorageNode storageNode2 = new StorageNode();
        storageNodes.add(storageNode2);
        StorageNode storageNode3 = new StorageNode();
        storageNodes.add(storageNode3);
        ReplicaGroup replicaGroup = new ReplicaGroup(storageNodes, 2);
        Client client = new Client(replicaGroup);

        List<Steppable> steppables = new LinkedList<>();
        steppables.add(storageNode1);
        steppables.add(storageNode2);
        steppables.add(storageNode3);
        steppables.add(replicaGroup);
        steppables.add(client);
        CompositeSteppable steppable = new ShufflingCompositeSteppable(steppables);

        Simulation simulation = new Simulation("Versioned storage service", "", steppable, new MaxIterationsCondition(100));
        simulation.run();
    }
}
