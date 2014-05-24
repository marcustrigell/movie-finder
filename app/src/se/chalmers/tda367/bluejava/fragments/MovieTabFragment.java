package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.tda367.bluejava.apis.HttpHandler;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.interfaces.HttpInteractor;
import se.chalmers.tda367.bluejava.models.Movie;

/**
 * An abstract class representing a "movie information" tab.
 * Each class extending this class is responsible for fetching
 * and displaying data related to itself.
 *
 * Nothing related to Activity or Context should be instantiated
 * before the Activity is fully functional.
 */
public abstract class MovieTabFragment extends Fragment implements HttpInteractor {

    protected Movie movie;

    protected MovieApi movieApi;

    protected Context context;

    protected AndroidHttpClient httpClient;

    protected HttpHandler httpHandler;

    /**
     * This is the first point where the activity can be referenced.
     * It signifies that the Activity has attached the fragment to itself.
     *
     * However, the Activity is not fully functional.
     * Neither are all its' views – so don't touch them here.
     *
     * But let's save the Activity (for later use).
     * We can now use this as our context throughout our tabs.
     */
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
    }

    /**
     * Here we create our actual view components by inflating a layout.
     *
     * Since the Activity still isn't fully functional, don't populate
     * any view components that need reference to it.
     *
     * So, just to be safe and keep it consistent, wait with everything
     * related to filling view components with actual data.
     */
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * The hosting Activity is now fully functional and its' views are
     * created. This is the right place to do Activity related stuff.
     *
     * Since the HttpClient and HttpHandler needs the Activity, this
     * is the first good spot to instantiate those. Call init() to do it.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    /**
     * This is where the fragment will start interacting with the user.
     * Here we get all the information about the current movie.
     *
     * This will eventually call the handleJSONResult() method.
     */
    @Override
    public void onResume() {
        super.onResume();
        sendHttpGetRequest();
    }

    /**
     * Initialization have to wait until the Activity is fully functional.
     * This method can be called once it is.
     */
    protected void init() {
        httpClient = HttpHandler.getAndroidHttpClient((Activity) context);
        httpHandler = new HttpHandler(httpClient);
        movieApi = new MovieApi();
        movie = getArguments().getParcelable("movie");
    }

    /**
     * Get more info about our movie.
     *
     * Performs a GET request to the API. When this is done
     * handleJSONResult() will be called to handle the response.
     */
    public abstract void sendHttpGetRequest();


    /**
     * Handles the callback from the API.
     * It takes care of actually creating and extending the objects needed.
     *
     * It's also responsible for calling appropriate method to populate
     * the fragment's view components with the new data.
     *
     * @param json The JSON result from the API
     */
    @Override
    public abstract void handleJSONResult(String json);
}