package se.chalmers.tda367.bluejava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Used to describe a crew member for a movie.
 * Could be a director, producer or such.
 *
 * Created by marcus on 2014-05-07.
 */
public class CrewMember extends Person {

    private final String CREDIT_ID, DEPARTMENT, JOB;

    public CrewMember(JSONObject jsonObject) throws JSONException {
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

    public static List<CrewMember> jsonToListOfCrewMembers(JSONObject jsonObject) {
        try {

            JSONArray crewArrray = jsonObject.getJSONArray("crew");
            List<CrewMember> crew = new LinkedList<CrewMember>();

            for (int i = 0; i < crewArrray.length(); ++i) {
                JSONObject crewJSON = crewArrray.getJSONObject(i);
                CrewMember crewMember = new CrewMember(crewJSON);
                crew.add(crewMember);
            }

            return crew;
        } catch (JSONException e) {
            throw new RuntimeException("CrewMember.java jsonToListOfCrewMembers error");
        }
    }
}
