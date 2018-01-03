package cf.janga.jsyms.examples.versionedstorageengine;

/**
 * A message indicating a write to a storage node has
 * succeeded.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class SNWriteSucceeded extends ItemMessage {

    private final String originalRequestId_;

    public SNWriteSucceeded(StorageNode source, String originalRequestId, Item originalItem) {
        super(source, originalItem);
        originalRequestId_ = originalRequestId;
    }

    public String getOriginalRequestId() {
        return originalRequestId_;
    }
}
