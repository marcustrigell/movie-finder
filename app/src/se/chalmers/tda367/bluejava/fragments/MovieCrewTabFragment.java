package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.adapters.CastTabArrayAdapter;
import se.chalmers.tda367.bluejava.adapters.CrewTabArrayAdapter;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.CrewMember;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.R;

import java.util.List;

public class MovieCrewTabFragment extends MovieTabFragment {

    public MovieCrewTabFragment(Activity activity, Movie movie) {
        super(activity, movie, R.layout.fragment_movie_crew);
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
     * Builds the screens layout.
     */
    public void setupLayout() {

        Credits credits = movie.getCredits();
        List<CrewMember> crew = credits.getCrew();

        ListView listView = (ListView) getView().findViewById(R.id.crewList);

        listView.setAdapter(new CrewTabArrayAdapter(getView().getContext(), activity, crew));
    }
}
