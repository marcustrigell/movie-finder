package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used to describe an actor in a movie.
 *
 * Created by marcus on 2014-05-07.
 */
public class Actor extends Person {

    private final String CAST_ID, CHARACTER, CREDIT_ID, ORDER;

    public Actor(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.CAST_ID = jsonObject.getString("cast_id");
        this.CHARACTER = jsonObject.getString("CHARACTER");
        this.CREDIT_ID = jsonObject.getString("credit_id");
        this.ORDER = jsonObject.getString("ORDER");
    }

    public String getCastID() {
        return this.CAST_ID;
    }

    public String getCharacter() {
        return this.CHARACTER;
    }

    public String getCreditID() {
        return this.CREDIT_ID;
    }

    public String getOrder() {
        return this.ORDER;
    }
}
