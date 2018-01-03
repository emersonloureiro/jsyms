package cf.janga.ds2.examples.versionedstorageengine;

/**
 * A message indicating a write to a replica group has
 * succeeded.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class RGWriteSucceeded extends ItemMessage {

    public RGWriteSucceeded(ReplicaGroup source, Item originalItem) {
        super(source, originalItem);
    }
}
