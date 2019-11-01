package cf.janga.jsyms.examples.cells;

import cf.janga.jsyms.core.FifoQueueMessageable;
import cf.janga.jsyms.messaging.Message;

import java.util.List;

public class RequestRouter extends FifoQueueMessageable {

    private final List<Cell> cells;

    public RequestRouter(List<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void processMessage(Message message) {
        if (message instanceof RouterRequestMessage) {
            RouterRequestMessage request = (RouterRequestMessage) message;
            long accountId = request.getAccountId();
            int cellNumber = (int) accountId % this.cells.size();
            Cell cell = this.cells.get(cellNumber);
            System.out.println(String.format("Request for account %s forwarded to cell %s", accountId, cellNumber));
            cell.doMessage(request);
        }
    }
}
