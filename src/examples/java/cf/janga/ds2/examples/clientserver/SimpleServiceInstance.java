package cf.janga.ds2.examples.clientserver;

import cf.janga.ds2.ext.backend.Request;
import cf.janga.ds2.ext.backend.ServiceInstance;

/**
 * Simple service instance for client/service example.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class SimpleServiceInstance extends ServiceInstance {

    @Override
    protected void processRequest(Request request) {
        System.out.println("Processed request " + request.getId());
    }
}
