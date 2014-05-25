package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents a person in a movie-context.
 * It must be able to hold different values at different times.
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

        if (jsonObject.isNull("profile_path")) {
            this.profilePath = null;
        } else {
            this.profilePath = jsonObject.getString("profile_path");
        }

		if (jsonObject.isNull("also_known_as")) {
			this.alsoKnownAs = null;
		} else {
			this.alsoKnownAs = jsonObject.getString("also_known_as");
		}

		if (jsonObject.isNull("biography")) {
			this.biography = null;
		} else {
			this.biography = jsonObject.getString("biography");
		}

		if (jsonObject.isNull("birthday")) {
			this.birthday = null;
		} else {
			this.birthday = jsonObject.getString("birthday");
		}

		if (jsonObject.isNull("deathday")) {
			this.deathday = null;
		} else {
			this.deathday = jsonObject.getString("deathday");
		}

		if (jsonObject.isNull("place_of_birth")) {
			this.placeOfBirth = null;
		} else {
			this.placeOfBirth = jsonObject.getString("place_of_birth");
		}

		if (jsonObject.isNull("popularity")) {
			this.popularity = 0;
		} else {
			this.popularity = jsonObject.getDouble("popularity");
		}
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
        this.id = in.readInt();
        this.name = in.readString();
        this.profilePath = in.readString();
        this.alsoKnownAs = in.readString();
        this.biography = in.readString();
        this.birthday = in.readString();
        this.deathday = in.readString();
        this.placeOfBirth = in.readString();
		this.popularity = in.readDouble();
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.profilePath);
        dest.writeString(this.alsoKnownAs);
        dest.writeString(this.biography);
        dest.writeString(this.birthday);
        dest.writeString(this.deathday);
		dest.writeString(this.placeOfBirth);
        dest.writeDouble(this.popularity);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Person createFromParcel(Parcel in) {
                    return new Person(in);
                }

                public Person[] newArray(int size) {
                    return new Person[size];
                }
            };
}
