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

    private final String name, id, profilePath;

    /** Constructor when a JSONObject is used */
    public Person(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.id = jsonObject.getString("id");
        this.profilePath = jsonObject.getString("profile_path");
    }

/*    *//** Constructor when JSONObject is not avaailable *//*
    public Person(String name, String id, String profilePath) {
        this.name = name;
        this.id = id;
        this.profilePath = profilePath;
    }*/

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getProfilePath() {
        return this.profilePath;
    }
}
