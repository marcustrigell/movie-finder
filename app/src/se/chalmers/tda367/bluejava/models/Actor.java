package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Used to describe an actor in a movie.
 *
 * Created by marcus on 2014-05-07.
 */
public class Actor extends Person {

    private final String CHARACTER, CREDIT_ID;
    private final int CAST_ID, ORDER;

    public Actor(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.CAST_ID = jsonObject.getInt("cast_id");
        this.CHARACTER = jsonObject.getString("character");
        this.CREDIT_ID = jsonObject.getString("credit_id");
        this.ORDER = jsonObject.getInt("order");
    }

    public int getCastID() {
        return this.CAST_ID;
    }

    public String getCharacter() {
        return this.CHARACTER;
    }

    public String getCreditID() {
        return this.CREDIT_ID;
    }

    public int getOrder() {
        return this.ORDER;
    }

    public static List<Actor> jsonToListOfActors(JSONObject jsonObject) {
        try {

            JSONArray castArray = jsonObject.getJSONArray("cast");
            List<Actor> cast = new LinkedList<Actor>();

            for (int i = 0; i < castArray.length(); ++i) {
                JSONObject actorJSON = castArray.getJSONObject(i);
                Actor actor = new Actor(actorJSON);
                cast.add(actor);
            }

            return cast;
        } catch (JSONException e) {
            throw new RuntimeException("Actor.java jsonToListOfActors error");
        }
    }

    protected Actor(Parcel in) {
        super(in);
        CHARACTER = in.readString();
        CREDIT_ID = in.readString();
        CAST_ID = in.readInt();
        ORDER = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CHARACTER);
        dest.writeString(CREDIT_ID);
        dest.writeInt(CAST_ID);
        dest.writeInt(ORDER);
    }

    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

	@Override
	public String toString() {
		return super.toString();
	}
}
