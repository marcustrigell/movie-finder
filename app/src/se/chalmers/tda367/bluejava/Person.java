package se.chalmers.tda367.bluejava;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used as template for a person in a movie-context.
 *
 * Created by marcus on 2014-05-07.
 *
 */
public abstract class Person implements Parcelable {

    private final String NAME, PROFILE_PATH;
    private final int ID;

    /** Constructor when a JSONObject is used */
    public Person(JSONObject jsonObject) throws JSONException {
        this.NAME = jsonObject.getString("name");
        this.ID = jsonObject.getInt("id");
        this.PROFILE_PATH = jsonObject.getString("profile_path");
    }

    public String getName() {
        return this.NAME;
    }

    public int getId() {
        return this.ID;
    }

    public String getProfilePath() {
        return this.PROFILE_PATH;
    }

    protected Person(Parcel in) {
        NAME = in.readString();
        PROFILE_PATH = in.readString();
        ID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(NAME);
        dest.writeValue(PROFILE_PATH);
        dest.writeInt(ID);
    }
}
