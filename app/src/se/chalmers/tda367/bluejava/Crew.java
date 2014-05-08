package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used to describe a crew member for a movie.
 * Could be a director, producer or such.
 *
 * Created by marcus on 2014-05-07.
 */
public class Crew extends Person {

    private final String CREDIT_ID, DEPARTMENT, JOB;

    public Crew(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.CREDIT_ID = jsonObject.getString("credit_id");
        this.DEPARTMENT = jsonObject.getString("DEPARTMENT");
        this.JOB = jsonObject.getString("JOB");
    }

    public String getCreditID() {
        return this.CREDIT_ID;
    }

    public String getDepartment() {
        return this.DEPARTMENT;
    }

    public String getJOB() {
        return this.JOB;
    }
}
