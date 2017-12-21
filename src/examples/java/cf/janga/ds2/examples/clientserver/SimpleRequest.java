package cf.janga.ds2.examples.clientserver;

import cf.janga.ds2.ext.backend.Request;

import java.util.UUID;

/**
 * Simple client/server example request.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class SimpleRequest implements Request {

    private final String id_;

    public SimpleRequest() {
        id_ = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id_;
    }
}
