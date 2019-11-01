package cf.janga.jsyms.examples.cells;

import cf.janga.jsyms.examples.Client;
import cf.janga.jsyms.examples.MessageFactory;
import cf.janga.jsyms.messaging.Message;

import java.util.Random;

public class RouterRequestMessageFactory implements MessageFactory {

    private long[] accountIds = new long[]{123895, 348789734, 837696, 286236, 64747, 47462901};

    private Random random;

    public RouterRequestMessageFactory() {
        this.random = new Random();
    }

    @Override
    public Message newMessage(Client client) {
        long accountId = this.accountIds[this.random.nextInt(this.accountIds.length)];
        return new RouterRequestMessage(client, accountId);
    }
}
