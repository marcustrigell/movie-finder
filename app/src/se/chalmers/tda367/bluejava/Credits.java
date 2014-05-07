package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

public class Credits {

    private final String creditsID;

    public Credits(JSONObject jsonObject) throws JSONException {
        creditsID = jsonObject.getString("id");
    }

    public String getCreditsID() {
        return creditsID;
    }
}
