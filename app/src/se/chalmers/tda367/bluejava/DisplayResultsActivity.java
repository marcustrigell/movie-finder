package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.display_results_activity);

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
        listView = (ListView) findViewById(R.id.results_list);
        //setContentView(listView);

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
        String toastMessage = (movies.size() > 0) ? "Yeey! I found " + movies.size() + " movies."
                : "Sorry! You must be a united fan.";
        showToast(toastMessage);


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

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    public void sortMovies(View view) {
        // Sortera
        showToast("Sorterar!!!");
    }
}
