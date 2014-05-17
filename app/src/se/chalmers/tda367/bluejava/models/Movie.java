package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Representing a movie
 *
 * By implementing the Parcelable interface,
 * a movie object can be passed along with intents
 * to start new activities.
 *
 * Using the builder pattern we are able to partially build
 * immutable objects. It allows us to create different
 * representations of a movie, using the same construction process.
 */
public class Movie implements Parcelable {

    private int id;
    private String title;
    private String releaseYear;
    private String popularity;
    private String rating;
    private String voteCount;
    private String posterPath;
    private String imdbID;
    private String budget;
    private String overview;
    private String tagline;
    private String runtime;
    private String revenue;
    private List<Genre> genres;
    private Credits credits;

    public static class Builder {

        // Required basic movie info
        private final int id;
        private final String title;
        private final String releaseYear;
        private final String popularity;
        private final String rating;
        private final String voteCount;
        private final String posterPath;

        // Additional movie info (optional)
        private List<Genre> genres;
        private String imdbID;
        private String budget;
        private String overview;
        private String tagline;
        private String runtime;
        private String revenue;

        // Movie credits (optional)
        private Credits credits;


        /**
         * This is the most basic movie,
         * all movies must hold this information.
         *
         * @param keys Table columns from db (declared in MovieContract)
         */
        public Builder(String[] keys) {
            id = Integer.parseInt(keys[0]);
            title = keys[1];
            releaseYear = keys[2];
            popularity = keys[3];
            rating = keys[4];
            voteCount = keys[5];
            posterPath = keys[6];
        }

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         * @param jsonObject A JSON object containing a movie
         * @throws JSONException
         */
        public Builder(JSONObject jsonObject) throws JSONException {
            id = jsonObject.getInt("id");
            title = jsonObject.getString("title");
            releaseYear = jsonObject.getString("release_date");
            popularity = jsonObject.getString("popularity");
            rating = jsonObject.getString("vote_average");
            voteCount = jsonObject.getString("vote_count");
            posterPath = jsonObject.getString("poster_path");
        }

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         * @param movie A Movie object that wants additional info
         */
        public Builder(Movie movie) {
            id = movie.getID();
            title = movie.getTitle();
            releaseYear = movie.getReleaseYear();
            popularity = movie.getPopularity();
            rating = movie.getRating();
            voteCount = movie.getVoteCount();
            posterPath = movie.getPosterPath();
        }

        /**
         * A builder method that adds additional info about a movie
         * @param jsonObject a JSON object holding movie details
         * @return itself
         * @throws JSONException
         */
        public Builder details(JSONObject jsonObject) throws JSONException {
            imdbID = jsonObject.getString("imdb_id");
            budget = jsonObject.getString("budget");
            overview = jsonObject.getString("overview");
            tagline = jsonObject.getString("tagline");
            runtime = jsonObject.getString("runtime");
            revenue = jsonObject.getString("revenue");

            JSONArray genresJson = jsonObject.getJSONArray("genres");
            genres = Genre.jsonToListOfGenres(genresJson);

            return this;
        }

        /**
         * A builder method that adds info about crew and actors
         * @param credits a Credits object holding crew and actors
         * @return itself
         */
        public Builder credits(Credits credits) {
            this.credits = credits;

            return this;
        }

        /**
         * The main builder.
         * This is where a movie gets actually created.
         */
        public Movie build() {
            return new Movie(this);
        }
    }

    /**
     * A complete movie holding all possible info
     * @param builder Object used to partially build the movie
     */
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
        credits = builder.credits;
    }

    @Override
    public String toString() {
        return title + " (" + getReleaseYear() + ") - " + rating;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getPopularity() { return popularity; }

    public String getRating() { return rating; }

    public String getVoteCount() { return voteCount; }

    public String getPosterPath() {
        return posterPath;
    }

    public List<Genre> getGenres() {
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

    public Credits getCredits() {
        return credits;
    }

    /**
     * Creates a list of movies and returns it
     * @param json A JSON object with movies
     * @return list of movies
     */
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

    /* Used when restoring object from parcel. */
    public Movie(Parcel in) { readFromParcel(in); }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(releaseYear);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(voteCount);
        dest.writeTypedList(genres);
        dest.writeString(imdbID);
        dest.writeString(budget);
        dest.writeString(overview);
        dest.writeString(tagline);
        dest.writeString(runtime);
        dest.writeString(revenue);
        dest.writeParcelable(credits, flags);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        releaseYear = in.readString();
        popularity = in.readString();
        rating = in.readString();
        voteCount = in.readString();
        if (genres == null) {
            genres = new ArrayList<Genre>();
        }
        in.readTypedList(genres, Genre.CREATOR);
        imdbID = in.readString();
        budget = in.readString();
        overview = in.readString();
        tagline = in.readString();
        runtime = in.readString();
        revenue = in.readString();
        credits = in.readParcelable(Credits.class.getClassLoader());
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
