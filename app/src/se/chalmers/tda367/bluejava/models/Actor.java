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

    private final String character;
	private final String creditId;
    private final int castId;
	private final int order;

	/**
	 * This is the type of Actor visible in movie details
	 */
    public Actor(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.castId = jsonObject.getInt("cast_id");
        this.character = jsonObject.getString("character");
        this.creditId = jsonObject.getString("credit_id");
        this.order = jsonObject.getInt("order");
    }

    public String getCharacter() {
        return this.character;
    }

    public String getCreditId() {
        return this.creditId;
    }

    public int getOrder() {
        return this.order;
    }

	public int getCastId() {
		return this.castId;
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
        character = in.readString();
        creditId = in.readString();
        castId = in.readInt();
        order = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(character);
        dest.writeString(creditId);
        dest.writeInt(castId);
        dest.writeInt(order);
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

}
