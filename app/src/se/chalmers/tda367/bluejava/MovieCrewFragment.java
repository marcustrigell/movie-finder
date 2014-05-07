package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieCrewFragment extends MovieFragment {

    public MovieCrewFragment(Activity activity, Movie movie) {
        super(activity, movie, R.layout.fragment_movie_crew);
    }

    /**
     * Get all info about our movie
     *
     * @param id The ID of the movie we want to search for
     */
    @Override
    protected void getAdditionalInfo(String id) {
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

    public void setupLayout() {
        TextView creditsTestTextView = (TextView) getView().findViewById(R.id.crewTest);
        creditsTestTextView.setText(movie.getCredits().getCreditsID());
    }
}
