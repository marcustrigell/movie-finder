package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for managing the credits in a movie.
 */
public class Credits implements Parcelable {

    private final int ID;

    private final List<Actor> CAST;

    private final List<CrewMember> CREW;

    public Credits(JSONObject jsonObject) throws JSONException {
        ID = jsonObject.getInt("id");
        CAST = Actor.jsonToListOfActors(jsonObject);
        CREW = CrewMember.jsonToListOfCrewMembers(jsonObject);
    }

    public int getID() {
        return ID;
    }

    public List<Actor> getCast() {
        return CAST;
    }

    public List<CrewMember> getCrew() {
        return CREW;
    }

    protected Credits(Parcel in) {
        ID = in.readInt();

        CAST = new ArrayList<Actor>();
        in.readTypedList(CAST, Actor.CREATOR);

        CREW = new ArrayList<CrewMember>();
        in.readTypedList(CREW, CrewMember.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeTypedList(CAST);
        dest.writeList(CREW);
    }

    public static final Parcelable.Creator<Credits> CREATOR = new Parcelable.Creator<Credits>() {
        @Override
        public Credits createFromParcel(Parcel in) {
            return new Credits(in);
        }

        @Override
        public Credits[] newArray(int size) {
            return new Credits[size];
        }
    };
}
