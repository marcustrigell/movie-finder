package se.chalmers.tda367.bluejava;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Movie implements Parcelable {
    private String id, title, releaseYear, popularity, rating, voteCount, posterPath;
    private MovieApi movieApi;

    public Movie(JSONObject jsonObject) {
        movieApi = new MovieApi();

        try {
            id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            releaseYear = jsonObject.getString("release_date");
            popularity = jsonObject.getString("popularity");
            rating = jsonObject.getString("vote_average");
            voteCount = jsonObject.getString("vote_count");
            posterPath = jsonObject.getString("poster_path");
        } catch (JSONException e) {
            throw new RuntimeException("JSON parsing in Movie.java error!!");
        }
    }

    public Movie(String json) throws JSONException {
        this(new JSONObject(json));
    }

    /* Used when restoring object from parcel. */
    public Movie(Parcel in) { readFromParcel(in); }

    @Override
    public String toString() {
        return title + " (" + getReleaseYear() + ") - " + rating;
    }

    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear.substring(0,4);
    }

    public String getPopularity() { return popularity; }

    public String getRating() { return rating; }

    public String getVoteCount() { return voteCount; }

    public String getPoster() {
        return "hej";
    }

    public static List<Movie> jsonToListOfMovies(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.getInt("total_results") == 0) {
                return null;
            }

            JSONArray movieArray = jsonObject.getJSONArray("results");
            List<Movie> movies = new LinkedList<Movie>();

            for (int i = 0; i < movieArray.length(); ++i) {
                JSONObject movieJSON = movieArray.getJSONObject(i);
                Movie movie = new Movie(movieJSON);
                movies.add(movie);
            }

            return movies;
        } catch (JSONException e) {
            throw new RuntimeException("Movie.java jsonToListOfMovies error");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(releaseYear);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(voteCount);
    }

    private void readFromParcel(Parcel in) {
        id = in.readString();
        title = in.readString();
        releaseYear = in.readString();
        popularity = in.readString();
        rating = in.readString();
        voteCount = in.readString();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Movie createFromParcel(Parcel in) {
                    return new Movie(in);
                }

                public Movie[] newArray(int size) {
                    return new Movie[size];
                }
            };
}
