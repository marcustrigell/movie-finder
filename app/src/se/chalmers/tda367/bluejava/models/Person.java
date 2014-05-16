package se.chalmers.tda367.bluejava.models;

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
public class Person implements Parcelable {

	private final int id;
	private final String name;
	private final String profilePath;
	private final String alsoKnownAs;
	private final String biography;
	private final String birthday;
	private final String deathday;
	private final String placeOfBirth;
	private final double popularity;

   	/** Constructor when a JSONObject is used */
    public Person(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.id = jsonObject.getInt("id");
        if(jsonObject.isNull("profile_path")) {
            this.profilePath = null;
        } else {
            this.profilePath = jsonObject.getString("profile_path");
        }
		if(jsonObject.isNull("also_known_as")) {
			this.alsoKnownAs = null;
		} else {
			this.alsoKnownAs = jsonObject.getString("also_known_as");
		}
		if(jsonObject.isNull("biography")) {
			this.biography = null;
		} else {
			this.biography = jsonObject.getString("biography");
		}
		if(jsonObject.isNull("birthday")) {
			this.birthday = null;
		} else {
			this.birthday = jsonObject.getString("birthday");
		}
		if(jsonObject.isNull("deathday")) {
			this.deathday = null;
		} else {
			this.deathday = jsonObject.getString("deathday");
		}
		if(jsonObject.isNull("place_of_birth")) {
			this.placeOfBirth = null;
		} else {
			this.placeOfBirth = jsonObject.getString("place_of_birth");
		}
		if(jsonObject.isNull("popularity")) {
			this.popularity = 0;
		} else {
			this.popularity = jsonObject.getDouble("popularity");
		}
    }

	@Override
	public String toString() {
		return name;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getAlsoKnownAs() {
		return this.alsoKnownAs;
	}

	public String getBiography() {
		return this.biography;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public String getDeathday() {
		return this.deathday;
	}

	public String getPlaceOfBirth() {
		return this.placeOfBirth;
	}

	public double getPopularity() {
		return this.popularity;
	}

	public String getProfilePath() {
		return this.profilePath;
	}

    protected Person(Parcel in) {
        this.name = in.readString();
        this.profilePath = in.readString();
        this.id = in.readInt();
		this.popularity = in.readDouble();
		this.placeOfBirth = in.readString();
		this.alsoKnownAs = in.readString();
		this.biography = in.readString();
		this.birthday = in.readString();
		this.deathday = in.readString();
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.profilePath);
        dest.writeInt(this.id);
		dest.writeDouble(this.popularity);
		dest.writeValue(this.placeOfBirth);
		dest.writeValue(this.alsoKnownAs);
		dest.writeValue(this.biography);
		dest.writeValue(this.birthday);
		dest.writeValue(this.deathday);
    }
}
