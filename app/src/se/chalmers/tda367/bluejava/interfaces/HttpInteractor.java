package se.chalmers.tda367.bluejava.interfaces;

public interface HttpInteractor extends JSONResultHandler {
    /**
     * This Interface provides operations to get and
     * handle information from an API.
     */
    public void sendHttpRequest();
}
