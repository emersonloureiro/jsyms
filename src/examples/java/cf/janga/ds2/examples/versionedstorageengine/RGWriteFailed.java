package cf.janga.ds2.examples.versionedstorageengine;

/**
 * A message indicating a write to a replica group has
 * succeeded.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class RGWriteFailed extends ItemMessage {

    private final String originalRequestId_;

    public RGWriteFailed(ReplicaGroup source, String originalRequestId, Item originalItem) {
        super(source, originalItem);
        originalRequestId_ = originalRequestId;
    }

    public String getOriginalRequestId() {
        return originalRequestId_;
    }
}
