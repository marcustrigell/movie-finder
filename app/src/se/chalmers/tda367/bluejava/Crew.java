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

    private String creditID, department, job;

    public Crew(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.creditID = jsonObject.getString("credit_id");
        this.department = jsonObject.getString("department");
        this.job = jsonObject.getString("job");
    }

    public String getCreditID() {
        return this.creditID;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getJob() {
        return this.job;
    }
}
