package cf.janga.jsyms.examples.versionedstorageengine;

import cf.janga.ds2.ext.backend.ServiceInstance;
import cf.janga.ds2.messaging.Message;

import java.util.HashMap;
import java.util.List;

/**
 * A replica group is a collection of storage nodes,
 * managing writes and reads to all of them.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class ReplicaGroup extends ServiceInstance {

    private final List<StorageNode> storageNodes_;
    private final HashMap<String, PendingRequest> pendingRequests_;
    private final int quorum_;

    public ReplicaGroup(List<StorageNode> storageNodes, int quorum) {
        storageNodes_ = storageNodes;
        pendingRequests_ = new HashMap<>(10);
        quorum_ = quorum;
    }

    @Override
    protected void processMessage(Message message) {
        if (message instanceof WriteMessage) {
            WriteMessage writeRequest = (WriteMessage) message;
            WriteMessage newWriteRequest = new WriteMessage(this, ((WriteMessage) message).getItem());
            for (StorageNode storageNode : storageNodes_) {
                storageNode.doMessage(newWriteRequest);
            }
            System.out.println("Sent write request " + message.getId() + " for item " + writeRequest.getItem().getId() + " to storage nodes");
            pendingRequests_.put(newWriteRequest.getId(), new PendingRequest(message));
        } else if (message instanceof SNWriteSucceeded) {
            SNWriteSucceeded writeSucceededMessage = (SNWriteSucceeded) message;
            Item writtenItem = writeSucceededMessage.getOriginalItem();
            PendingRequest pendingRequest = pendingRequests_.get(writeSucceededMessage.getOriginalRequestId());
            // Can't receive a write success message from a SN if we
            // hadn't sent a write message to that node in the first place
            assert pendingRequest != null;
            pendingRequest.confirmWrite();
            int confirmedWrites = pendingRequest.getConfirmedWrites();
            if (confirmedWrites == quorum_) {
                System.out.println("Successfully wrote item " + writtenItem.getId() + " from request " + writeSucceededMessage.getOriginalRequestId());
                pendingRequest.getOriginalMessage().getSource().doMessage(new RGWriteSucceeded(this, writtenItem));
            } else if (confirmedWrites < quorum_) {
                System.out.println("Item " + writtenItem.getId() + " from request " + writeSucceededMessage.getOriginalRequestId() + " only has " + confirmedWrites + " confirmed writes so far");
            } else if (confirmedWrites > quorum_) {
                System.out.println("Item " + writtenItem.getId() + " from request " + writeSucceededMessage.getOriginalRequestId() + " already successfully written ");
            }
        } else if (message instanceof SNWriteFailed) {
            SNWriteFailed writeFailedMessage = (SNWriteFailed) message;
            Item originalItem = writeFailedMessage.getOriginalItem();
            System.out.println("Failed to write item " + originalItem.getId() + " from request " + writeFailedMessage.getOriginalRequestId());
            doMessage(new RGWriteFailed(this, writeFailedMessage.getOriginalRequestId(), originalItem));
        }
    }

    private class PendingRequest {
        private final Message originalRequest_;

        private int confirmedWrites_;

        PendingRequest(Message originalRequest) {
            originalRequest_ = originalRequest;
            confirmedWrites_ = 0;
        }

        void confirmWrite() {
            confirmedWrites_++;
        }

        int getConfirmedWrites() {
            return confirmedWrites_;
        }

        Message getOriginalMessage() {
            return originalRequest_;
        }
    }
}
