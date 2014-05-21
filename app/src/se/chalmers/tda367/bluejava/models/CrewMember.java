package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
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

    private String creditId;
	private String department;
	private String job;

    public CrewMember(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.creditId = jsonObject.getString("credit_id");
        this.department = jsonObject.getString("department");
        this.job = jsonObject.getString("job");
    }

    public String getCreditID() {
        return this.creditId;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getJOB() {
        return this.job;
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

    protected CrewMember(Parcel in) {
        super(in);
        creditId = in.readString();
        department = in.readString();
        job = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(creditId);
        dest.writeString(department);
        dest.writeString(job);
    }

    public static final Parcelable.Creator<CrewMember> CREATOR = new Parcelable.Creator<CrewMember>() {
        @Override
        public CrewMember createFromParcel(Parcel in) {
            return new CrewMember(in);
        }

        @Override
        public CrewMember[] newArray(int size) {
            return new CrewMember[size];
        }
    };
}
