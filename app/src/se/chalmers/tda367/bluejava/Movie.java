package se.chalmers.tda367.bluejava;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Movie implements Parcelable {

    private String id, title, releaseYear, popularity, rating, voteCount,
            posterPath, imdbID, budget, overview, tagline, runtime, revenue;

    private List<String> genres;

    public static class Builder {

        // Required
        private final String id, title, releaseYear, popularity, rating, voteCount, posterPath;

        // Might be added later
        private List<String> genres;
        private String imdbID, budget, overview, tagline, runtime, revenue;

        public Builder(JSONObject jsonObject) throws JSONException {
            id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            releaseYear = jsonObject.getString("release_date");
            popularity = jsonObject.getString("popularity");
            rating = jsonObject.getString("vote_average");
            voteCount = jsonObject.getString("vote_count");
            posterPath = jsonObject.getString("poster_path");
        }

        public Builder details(JSONObject jsonObject) throws JSONException {
            imdbID = jsonObject.getString("imdb_id");
            budget = jsonObject.getString("budget");
            overview = jsonObject.getString("overview");
            tagline = jsonObject.getString("tagline");
            runtime = jsonObject.getString("runtime");
            revenue = jsonObject.getString("revenue");

            genres = new ArrayList<String>();
            JSONArray genresJson = jsonObject.getJSONArray("genres");

            for (int i = 0; i < genresJson.length(); i++) {
                genres.add(genresJson.get(i).toString());
            }

            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    private Movie(Builder builder) {
        id = builder.id;
        title = builder.title;
        releaseYear = builder.releaseYear;
        popularity = builder.popularity;
        rating = builder.rating;
        voteCount = builder.voteCount;
        posterPath = builder.posterPath;
        genres = builder.genres;
        imdbID = builder.imdbID;
        budget = builder.budget;
        overview = builder.overview;
        tagline = builder.tagline;
        runtime = builder.runtime;
        revenue = builder.revenue;
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
        /* Crashar */
        /* return releaseYear.substring(0,4); */
        return releaseYear;
    }

    public String getPopularity() { return popularity; }

    public String getRating() { return rating; }

    public String getVoteCount() { return voteCount; }

    public String getPosterPath() {
        return posterPath;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getBudget() {
        return budget;
    }

    public String getOverview() {
        return overview;
    }

    public String getTagline() {
        return tagline;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getRevenue() {
        return revenue;
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
                Movie movie = new Builder(movieJSON).build();
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
        dest.writeString(posterPath);
        dest.writeString(releaseYear);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(voteCount);
    }

    private void readFromParcel(Parcel in) {
        id = in.readString();
        title = in.readString();
        posterPath = in.readString();
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
