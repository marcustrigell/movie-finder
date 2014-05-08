package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used to describe an actor in a movie.
 *
 * Created by marcus on 2014-05-07.
 */
public class Actor extends Person {

    private String castID, character, creditID, order;

    public Actor(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.castID = jsonObject.getString("cast_id");
        this.character = jsonObject.getString("character");
        this.creditID = jsonObject.getString("credit_id");
        this.order = jsonObject.getString("order");
    }

    public String getCastID() {
        return this.castID;
    }

    public String getCharacter() {
        return this.character;
    }

    public String getCreditID() {
        return this.creditID;
    }

    public String getOrder() {
        return this.order;
    }
}
