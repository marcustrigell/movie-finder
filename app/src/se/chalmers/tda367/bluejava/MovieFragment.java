package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MovieFragment extends Fragment implements JSONResultHandler {
    protected Activity activity;

    protected Movie movie;

    protected MovieApi movieApi;

    public MovieFragment(Activity activity, Movie movie) {

        this.movie = movie;

        this.activity = activity;

        movieApi = new MovieApi();
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState);

    /**
     * Get more info about our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    protected abstract void getAdditionalInfo(String id);


    /**
     * Handles the callback from the API
     *
     * @param json The JSON result from the API
     */
    public abstract void handleJSONResult(String json);
}
