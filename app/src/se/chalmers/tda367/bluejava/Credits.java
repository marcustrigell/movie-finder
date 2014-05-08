package se.chalmers.tda367.bluejava;

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

    private final int creditsID;

    private final List<Actor> cast;

    private final List<CrewMember> crew;

    public Credits(JSONObject jsonObject) throws JSONException {
        creditsID = jsonObject.getInt("id");
        cast = Actor.jsonToListOfActors(jsonObject);
        crew = CrewMember.jsonToListOfCrewMembers(jsonObject);
    }

    public int getID() {
        return creditsID;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    protected Credits(Parcel in) {
        creditsID = in.readInt();

        cast = new ArrayList<Actor>();
        in.readTypedList(cast, Actor.CREATOR);

        crew = new ArrayList<CrewMember>();
        in.readTypedList(crew, CrewMember.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(creditsID);
        dest.writeTypedList(cast);
        dest.writeList(crew);
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
