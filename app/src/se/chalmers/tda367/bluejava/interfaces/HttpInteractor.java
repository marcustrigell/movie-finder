package se.chalmers.tda367.bluejava.interfaces;

/**
 * This Interface provides operations to get and
 * handle information from an API.
 */
public interface HttpInteractor extends JSONResultHandler {

    /**
     * Sends a GET request to the server
     * The server's returned result will be handled
     * by the handleJSONResult().
     */
    public void sendHttpGetRequest();
}
