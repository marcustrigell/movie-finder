package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.CrewTabArrayAdapter;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.CrewMember;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

public class MovieCrewTabFragment extends MovieTabFragment {

    private ListView listView;

    public static MovieCrewTabFragment newInstance(Movie movie) {
        MovieCrewTabFragment tab = new MovieCrewTabFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);

        tab.setArguments(bundle);

        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_crew, container, false);
        listView = (ListView) view.findViewById(R.id.crewList);
        return view;
    }

    protected void getAdditionalInfo(int id) {

        if (movie.getCredits() == null) {
            httpHandler.get(movieApi.getMovieCreditsQuery(id), this);
        } else {
            populateLayout();
        }
    }

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

        populateLayout();
    }

    /**
     * Populates the fragment's view with crew information
     */
    public void populateLayout() {
        Credits credits = movie.getCredits();
        List<CrewMember> crew = credits.getCrew();
        listView.setAdapter(new CrewTabArrayAdapter(context, (Activity) context, crew));
    }
}
