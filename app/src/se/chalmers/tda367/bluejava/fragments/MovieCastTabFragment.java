package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.CastTabArrayAdapter;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.Movie;

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
     * Get videos of our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    @Override
    protected void getMovieVideos(int id) {
        httpHandler.get(movieApi.getMovieVideosQuery(id), this);
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

        Credits credits = movie.getCredits();
        List<Actor> cast = credits.getCast();

        ListView listView = (ListView) getView().findViewById(R.id.castList);

        listView.setAdapter(new CastTabArrayAdapter(getView().getContext(), activity, cast));

    }
}
