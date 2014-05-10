package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.R;

import java.util.List;

public class MovieCastTabFragment extends MovieTabFragment {

    public MovieCastTabFragment(Activity activity, Movie movie) {
        super(activity, movie, R.layout.fragment_movie_cast);
    }

    /**
     * Get all info about our movie
     *
     * @param id The ID of the movie we want to search for
     */
    @Override
    protected void getAdditionalInfo(int id) {
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
/*        TextView creditsIDTextView = (TextView) getView().findViewById(R.id.creditsID);
        String id = Integer.toString(movie.getCredits().getID());
        creditsIDTextView.setText(id);*/

        Credits credits = movie.getCredits();
        List<Actor> cast = credits.getCast();



    }
}
