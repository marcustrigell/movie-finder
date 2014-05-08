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

    private final String NAME, PROFILE_PATH;
    private final int ID;

    /** Constructor when a JSONObject is used */
    public Person(JSONObject jsonObject) throws JSONException {
        this.NAME = jsonObject.getString("name");
        this.ID = jsonObject.getInt("id");
        if(jsonObject.isNull("profile_path")) {
            this.PROFILE_PATH = null;
        } else {
            this.PROFILE_PATH = jsonObject.getString("profile_path");
        }
    }

    public String getName() {
        return this.NAME;
    }

    public int getId() {
        return this.ID;
    }

    public String getProfilePath() {
        return this.PROFILE_PATH;
    }


}
