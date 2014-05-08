package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.app.Fragment;
import android.net.http.AndroidHttpClient;

public class TabFragment extends Fragment implements JSONResultHandler {

    protected Activity activity;

    protected MovieApi movieApi;

    protected int layoutID;

    protected AndroidHttpClient httpClient;

    protected HttpHandler httpHandler;

    public TabFragment(Activity activity, int layoutID) {

        this.activity = activity;

        this.layoutID = layoutID;

        httpClient = HttpHandler.getAndroidHttpClient(activity);

        httpHandler = new HttpHandler(httpClient);

        movieApi = new MovieApi();

    }

    /**
     * Handles the string containing the JSON object
     *
     * @param json The JSON result from the API
     */
    @Override
    public void handleJSONResult(String json) {

    }
}
