package cf.janga.ds2.ext.backend;

/**
 * An entity that can receive requests from another entity.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public interface Requestable {

    /**
     * Requests the execution of the request provided.
     *
     * @param request Request to be executed
     */
    void doRequest(Request request);
}
