package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.tda367.bluejava.apis.HttpHandler;
import se.chalmers.tda367.bluejava.interfaces.JSONResultHandler;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.apis.MovieApi;

public abstract class MovieTabFragment extends Fragment implements JSONResultHandler {
    protected Activity activity;

    protected Movie movie;

    protected MovieApi movieApi;

    protected int layoutID;

    protected AndroidHttpClient httpClient;

    protected HttpHandler httpHandler;

    public MovieTabFragment(Activity activity, Movie movie, int layoutID) {

        this.movie = movie;

        this.activity = activity;

        this.layoutID = layoutID;

        httpClient = HttpHandler.getAndroidHttpClient(activity);

        httpHandler = new HttpHandler(httpClient);

        movieApi = new MovieApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutID, container, false);

        getAdditionalInfo(movie.getID());

       // getMovieVideos(movie.getID());

        return rootView;
    }

    /**
     * Get more info about our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    protected abstract void getAdditionalInfo(int id);

    /**
     * Get videos of our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    protected abstract void getMovieVideos(int id);

    /**
     * Handles the callback from the API
     *
     * @param json The JSON result from the API
     */
    public abstract void handleJSONResult(String json);
}
