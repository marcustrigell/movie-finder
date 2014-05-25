package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.MovieCoversAdapter;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * This is an abstract class representing a tab page
 * on the homescreen. It's responsible for both getting
 * and showing movie covers in a nice grid view.
 */
public abstract class TabFragmentMain extends TabFragment {

    private List<Movie> movies;

    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_gridview, container, false);

        gridView = (GridView) view.findViewById(R.id.popularGridview);

        return view;
    }

    @Override
    public void sendHttpGetRequest() {

        if (movies == null || movies.size() == 0) {
            httpHandler.get(movieApi.getPopularMoviesQuery(), this);
        }
    }

    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        movies = Movie.jsonToListOfMovies(json);

        populateLayout(movies);
    }

    public void populateLayout(List<Movie> movies) {
        gridView.setAdapter(new MovieCoversAdapter(context, (Activity) context, movies));
    }
}
