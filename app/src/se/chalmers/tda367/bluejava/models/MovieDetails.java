package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails implements Parcelable {

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

    public MovieDetails(Parcel in) { readFromParcel(in); }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(genres);
        dest.writeString(imdbID);
        dest.writeString(budget);
        dest.writeString(overview);
        dest.writeString(tagline);
        dest.writeString(runtime);
        dest.writeString(revenue);
    }

    private void readFromParcel(Parcel in) {

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
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public MovieDetails createFromParcel(Parcel in) {
                    return new MovieDetails(in);
                }

                public MovieDetails[] newArray(int size) {
                    return new MovieDetails[size];
                }
            };
}
