package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used as template for a person in a movie-context.
 *
 * Created by marcus on 2014-05-07.
 *
 */
public abstract class Person {

    private final String NAME, ID, PROFILE_PATH;

    /** Constructor when a JSONObject is used */
    public Person(JSONObject jsonObject) throws JSONException {
        this.NAME = jsonObject.getString("name");
        this.ID = jsonObject.getString("id");
        this.PROFILE_PATH = jsonObject.getString("profile_path");
    }

    public String getName() {
        return this.NAME;
    }

    public String getId() {
        return this.ID;
    }

    public String getProfilePath() {
        return this.PROFILE_PATH;
    }
}
