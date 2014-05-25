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
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.CrewMember;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * Displays a movie's crew in a list view
 * in one of the DisplayMovieActivity's tabs.
 *
 * Choosing a crew member in the list should show
 * detailed information about it.
 */
public class TabFragmentMovieCrew extends TabFragment {

    private Movie movie;

    private ListView listView;

    public static TabFragmentMovieCrew newInstance(Movie movie) {
        TabFragmentMovieCrew tab = new TabFragmentMovieCrew();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BlueJava.EXTRA_MOVIE, movie);

        tab.setArguments(bundle);

        return tab;
    }

    @Override
    public void init() {
        super.init();
        movie = getArguments().getParcelable(BlueJava.EXTRA_MOVIE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_crew, container, false);
        listView = (ListView) view.findViewById(R.id.crewList);
        return view;
    }

    public void sendHttpGetRequest() {

        if (movie.getCredits() == null) {
            httpHandler.get(movieApi.getMovieCreditsQuery(movie.getID()), this);
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
