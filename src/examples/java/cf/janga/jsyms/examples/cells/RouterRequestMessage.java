package cf.janga.jsyms.examples.cells;

import cf.janga.jsyms.examples.clientserver.Request;
import cf.janga.jsyms.messaging.Messageable;

public class RouterRequestMessage extends Request {

    private final long accountId;

    public RouterRequestMessage(Messageable source, long accountId) {
        super(source);
        this.accountId = accountId;
    }

    public long getAccountId() {
        return this.accountId;
    }
}
