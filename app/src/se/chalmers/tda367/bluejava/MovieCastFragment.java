package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.graphics.Color;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieCastFragment extends MovieFragment {

    public MovieCastFragment(Activity activity, Movie movie) {
        super(activity, movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_cast, container, false);

        getAdditionalInfo(movie.getID());

        return rootView;
    }

    /**
     * Get all info about our movie
     *
     * @param id The ID of the movie we want to search for
     */
    @Override
    protected void getAdditionalInfo(String id) {
        AndroidHttpClient httpClient = HttpHandler.getAndroidHttpClient(activity);

        HttpHandler httpHandler = new HttpHandler(httpClient);

        httpHandler.get(movieApi.getMovieCreditsQuery(id), this);
    }

    /**
     * Handles the callback from the API
     *
     * @param json The JSON result from the API
     */
    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        try {
            Credits credits = new Credits(new JSONObject(json));
            movie = new Movie.Builder(movie).credits(credits).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setupLayout();
    }

    /**
     * Builds the screen's layout
     */
    public void setupLayout() {
        TextView creditsIDTextView = (TextView) getView().findViewById(R.id.creditsID);
        creditsIDTextView.setText(movie.getCredits().getCreditsID());
        creditsIDTextView.setTextColor(Color.RED);
    }
}
