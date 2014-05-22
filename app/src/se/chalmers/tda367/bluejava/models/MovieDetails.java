package se.chalmers.tda367.bluejava.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieDetails {

    private List<Genre> genres;
    private String imdbID;
    private String budget;
    private String overview;
    private String tagline;
    private String runtime;
    private String revenue;

    public MovieDetails(JSONObject jsonObject) throws JSONException {

        JSONArray genresJson = jsonObject.getJSONArray("genres");
        genres = Genre.jsonToListOfGenres(genresJson);

        imdbID = jsonObject.getString("imdb_id");
        budget = jsonObject.getString("budget");
        overview = jsonObject.getString("overview");
        tagline = jsonObject.getString("tagline");
        runtime = jsonObject.getString("runtime");
        revenue = jsonObject.getString("revenue");
    }
}
