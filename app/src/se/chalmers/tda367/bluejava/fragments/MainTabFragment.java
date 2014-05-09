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
import se.chalmers.tda367.bluejava.apis.MovieApi;

public abstract class MainTabFragment extends Fragment implements JSONResultHandler {

    protected Activity activity;

    protected MovieApi movieApi;

    protected int layoutID;

    protected AndroidHttpClient httpClient;

    protected HttpHandler httpHandler;

    public MainTabFragment(Activity activity, int layoutID) {
        this.activity = activity;

        this.layoutID = layoutID;

        httpClient = HttpHandler.getAndroidHttpClient(activity);

        httpHandler = new HttpHandler(httpClient);

        movieApi = new MovieApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(layoutID, container, false);

        return rootView;
    }

    protected abstract void getMovies();

    @Override
    public abstract void handleJSONResult(String json);
}
