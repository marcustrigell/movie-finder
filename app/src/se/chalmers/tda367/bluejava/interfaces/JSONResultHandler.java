package se.chalmers.tda367.bluejava.interfaces;

public interface JSONResultHandler {
    /**
     * This Interface provides a method to handle
     * JSON results (strings) from the API.
     * @param json The JSON result from the API
     */
    public void handleJSONResult(String json);
}
