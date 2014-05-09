package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.ImageAdapter;

import java.util.List;

public class MainPopularFragment extends MainTabFragment {

    private final Activity activity;

    private final int layoutID;

    public MainPopularFragment(Activity activity, int layoutID) {

        super(activity, layoutID);

        this.activity = activity;

        this.layoutID = layoutID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(layoutID, container, false);

        getMovies();

        return view;
    }

    protected void getMovies() {
        httpHandler.get(movieApi.getPopularMoviesQuery(), this);
    }

    /**
     * Handles the string containing the JSON object
     *
     * @param json The JSON result from the API
     */
    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        /*
         * Take the string and make a lot of movies from it
         */
        List<Movie> movies = Movie.jsonToListOfMovies(json);

        GridView gridView = (GridView) getView().findViewById(R.id.popularGridview);

        gridView.setAdapter(new ImageAdapter(getView().getContext(), activity, movies));
    }
}
