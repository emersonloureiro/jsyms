package cf.janga.ds2.examples.versionedstorageengine;

/**
 * A message indicating that a write to a storage node has failed.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class SNWriteFailed extends ItemMessage {

    private String message_;
    private final String originalRequestId_;

    public SNWriteFailed(StorageNode source, String originalRequestId, Item originalItem, String message) {
        super(source, originalItem);
        message_ = message;
        originalRequestId_ = originalRequestId;
    }

    public String getOriginalRequestId() {
        return originalRequestId_;
    }
}
