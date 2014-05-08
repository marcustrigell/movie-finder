package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

public class MainPopularFragment extends MainTabFragment {

    private GridView gridView;

    private final Activity activity;

    public MainPopularFragment(Activity activity) {
        super(activity, R.layout.fragment_main_popular);

        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_popular, container, false);

        gridView = (GridView) view.findViewById(R.id.popularGridview);

        getMovies();

        return view;
    }

    protected void getMovies() {
        httpHandler.get(movieApi.getTopRatedMoviesQuery(), this);
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

        gridView.setAdapter(new ImageAdapter(getView().getContext(), activity, movies));
    }
}
