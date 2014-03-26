package se.chalmers.tda367.bluejava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Movie {
    private final String id, title, releaseDate, popularity, rating, voteCount;

    public Movie(JSONObject jsonObject) {
        try {
            id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            releaseDate = jsonObject.getString("release_date");
            popularity = jsonObject.getString("popularity");
            rating = jsonObject.getString("vote_average");
            voteCount = jsonObject.getString("vote_count");
        } catch (JSONException e) {
            throw new RuntimeException("JSON parsing in Movie.java error!!");
        }
    }

    public Movie(String json) throws JSONException {
        this(new JSONObject(json));
    }

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
        return releaseDate.substring(0,4);
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
}
