package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DisplayResultsActivity extends Activity {

    /**
     * Used for some sweet filtering in the logcat
     */
    private static final String TAG = "DisplayResultsActivity";

    private AndroidHttpClient httpClient;
    private HttpHandler httpHandler;
    private ListView listView;
    private MovieApi movieApi;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Catching the intent and the search query passed along with it
         */
        Intent intent = getIntent();
        String query = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        /**
         * Used to create appropriate URLs for our http requests
         */
        movieApi = new MovieApi();

        /**
         * Handling all http requests and communication with our APIs
         * Once done with a request, it'll call handleSearchResults
         * and pass a string containing the result
         */
        httpClient = HttpHandler.getAndroidHttpClient(this);
        httpHandler = new HttpHandler(httpClient);


        /**
         * New view to display a list of movies
         */
        listView = new ListView(this);
        setContentView(listView);

        findMovies(query);
    }

    public void handleSearchResults(String json) {

        if (json == null) {
            return;
        }

        /**
         * Take the string and make a lot of movies from it
         */
        final List<Movie> movies = Movie.jsonToListOfMovies(json);

        /**
         * Give the user som feedback on their search
         */
        showToast(movies.size());

        /**
         * Take our list with movies and display them on our listview
         * Read more:
         * https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
         */
        ArrayAdapter adapter = new ArrayAdapter<Movie>(this,
                android.R.layout.simple_list_item_1, movies);
        listView.setAdapter(adapter);
    }

    public void findMovies(String title) {
        httpHandler.get(movieApi.createMovieQuery(title), this);
    }

    private void showToast(int numberOfResults) {
        String toastMessage = (numberOfResults > 0) ? "Yeey! I found " + numberOfResults + " movies."
                : "Sorry! You must be a united fan.";

        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_LONG).show();
    }

    public void changeOrder() {

    }
}
