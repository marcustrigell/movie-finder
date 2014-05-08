package se.chalmers.tda367.bluejava;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Representing a movie genre
 */
public class Genre implements Parcelable {

    private final int ID;

    private final String name;

    public Genre(JSONObject jsonObject) throws JSONException {
        this.ID = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    /**
     * Creates a list of genres and returns it
     * @param jsonArray A JSON array with genres
     * @return list of genres
     */
    public static List<Genre> jsonToListOfGenres(JSONArray jsonArray) {

        try {
            List<Genre> genres = new ArrayList<Genre>();

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonGenre = jsonArray.getJSONObject(i);
                Genre genre = new Genre(jsonGenre);
                genres.add(genre);
            }

            return genres;

        } catch (JSONException e) {
            throw new RuntimeException("Actor.java jsonToListOfActors error");
        }
    }

    protected Genre(Parcel in) {
        ID = in.readInt();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Genre> CREATOR = new Parcelable.Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}

