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

	/*public static class Builder {

		// Required basic Person info
		private final int id;
		private final String name;
		private final String profilePath;
		private final double popularity;

		// More detailed Person info
		private String alsoKnownAs;
		private String biography;
		private String birthday;
		private String deathday;
		private String placeOfBirth;
		private String url;

		*//**
		 * This is a Person with basic information
		 * @param jsonObject A JSON object containing an Person
		 * @throws JSONException
		 *//*
		public Builder(JSONObject jsonObject) throws JSONException {
			id = jsonObject.getInt("id");
			name = jsonObject.getString("name");
			profilePath = jsonObject.getString("profile_path");
			popularity = jsonObject.getDouble("popularity");
		}

		// Kanske inte behövs

		*//**
		 * This is the most basic Person
		 * all persons must hold this information.
		 * @param person A Person object that wants additional info
		 *//*
		public Builder(Person person) {
			id = person.getId();
			name = person.getName();
			profilePath = person.getProfilePath();
			popularity = person.getPopularity();
		}

		*//**
		 * A builder method that adds additional info about an actor.
		 * This specific method is used for ID query and shown in DisplayActorActivity.
		 * @param jsonObject a JSON object holding actor details
		 * @return itself
		 * @throws JSONException
		 *//*
		public Builder personDetails(JSONObject jsonObject) throws JSONException {
			alsoKnownAs = jsonObject.getString("also_known_as");
			biography = jsonObject.getString("biography");
			birthday = jsonObject.getString("birthday");
			deathday = jsonObject.getString("deathday");
			placeOfBirth = jsonObject.getString("place_of_birth");

			return this;
		}

		*//**
		 * The main builder.
		 * This is where a person actually gets created.
		 *//*
		public Person build() {
			return new Person(this);
		}
	}

	*//**
	 * A complete person holding all possible info
	 * @param builder Object used to partially build the person
	 *//*
	private Person(Builder builder) {
		id = builder.id;
		name = builder.name;
		alsoKnownAs = builder.alsoKnownAs;
		biography = builder.biography;
		birthday = builder.birthday;
		deathday = builder.deathday;
		placeOfBirth = builder.placeOfBirth;
		popularity = builder.popularity;
		profilePath = builder.profilePath;
		url = builder.url;
	}*/

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
