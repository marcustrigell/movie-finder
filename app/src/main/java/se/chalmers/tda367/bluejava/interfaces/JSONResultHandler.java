package se.chalmers.tda367.bluejava.interfaces;

public interface JSONResultHandler {
    /**
     * Handles the string containing the JSON object
     * @param json The JSON result from the API
     */
    public void handleJSONResult(String json);
}
