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
 * to start new activities. All objects hold by this
 * class should also implement the Parcelable interface.
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
    private MovieDetails details;
    private Credits credits;
    private List<Video> videos;

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
        private MovieDetails details;

        // Movie credits (optional)
        private Credits credits;

        // Movie videos, trailers etc (optional)
        private List<Video> videos;

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         */
        public Builder(
            int id,
            String title,
            String releaseYear,
            String popularity,
            String rating,
            String voteCount,
            String posterPath
        ) {

            this.id = id;
            this.title = title;
            this.releaseYear = releaseYear;
            this.popularity = popularity;
            this.rating = rating;
            this.voteCount = voteCount;
            this.posterPath = posterPath;
        }

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         *
         * @param keys Table columns from db (declared in MovieContract)
         */
        public Builder(String[] keys) {
            this(
                Integer.parseInt(keys[0]),
                keys[1],
                keys[2],
                keys[3],
                keys[4],
                keys[5],
                keys[6]
            );
        }

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         * @param jsonObject A JSON object containing a movie
         * @throws JSONException
         */
        public Builder(JSONObject jsonObject) throws JSONException {
            this(
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getString("release_date"),
                jsonObject.getString("popularity"),
                jsonObject.getString("vote_average"),
                jsonObject.getString("vote_count"),
                jsonObject.getString("poster_path")
            );

        }

        /**
         * This is the most basic movie,
         * all movies must hold this information.
         * @param movie A Movie object that wants additional info
         */
        public Builder(Movie movie) {
            this(
                movie.getID(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getPopularity(),
                movie.getRating(),
                movie.getVoteCount(),
                movie.getPosterPath()
            );
        }

        /**
         * A builder method that adds additional info about a movie
         * @param details a details object holding movie details
         * @return itself
         * @throws JSONException
         */
        public Builder details(MovieDetails details) throws JSONException {
            this.details = details;

            return this;
        }

        /**
         * A builder method that adds associated videos to a movie.
         * @param jsonObject a JSON object holding movie details
         * @return itsef
         * @throws JSONException
         */
        public Builder videos(JSONObject jsonObject) throws JSONException {
            JSONArray videosJson = jsonObject.getJSONArray("results");
            videos = Video.jsonToListOfVideos(videosJson);

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
        details = builder.details;
        credits = builder.credits;
        videos = builder.videos;
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

    public MovieDetails getDetails() {
        return details;
    }

    public Credits getCredits() {
        return credits;
    }

    public List<Video> getVideos() {
        return videos;
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
        dest.writeParcelable(details, flags);
        dest.writeParcelable(credits, flags);
        dest.writeTypedList(videos);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        releaseYear = in.readString();
        popularity = in.readString();
        rating = in.readString();
        voteCount = in.readString();
        details = in.readParcelable(MovieDetails.class.getClassLoader());
        credits = in.readParcelable(Credits.class.getClassLoader());
        if (videos == null) {
            videos = new ArrayList<Video>();
        }
        in.readTypedList(videos, Video.CREATOR);
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
