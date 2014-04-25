package se.chalmers.tda367.bluejava;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class DisplayResultsActivity extends ListActivity {

    private AndroidHttpClient httpClient;

    private HttpHandler httpHandler;

    private MovieApi movieApi;

    private List<Movie> movies;

    private ISort sortMethod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_results_activity);

		// Get the action bar
		ActionBar actionBar = getActionBar();

		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);

        /*
         * Used to create appropriate URLs for our http requests
         */
        movieApi = new MovieApi();

        /*
         * Handling all http requests and communication with our APIs
         * Once done with a request, it'll call handleSearchResults
         * and pass a string containing the result
         */
        httpClient = HttpHandler.getAndroidHttpClient(this);
        httpHandler = new HttpHandler(httpClient);

        /* Set default sort method to sort by title in ascending order. */
        sortMethod = new SortByTitle();

		handleIntent(getIntent());
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		// Associate the searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

    public void handleSearchResults(String json) {

        if (json == null) {
            return;
        }

        /*
         * Take the string and make a lot of movies from it
         */
        movies = Movie.jsonToListOfMovies(json);

        /*
         * Give the user som feedback on their search
         */
        String toastMessage = (movies != null)
                ? "Yeey! I found " + movies.size() + " movies."
                : "Sorry! You must be a united fan.";
        showToast(toastMessage);

        displayMovies(movies);
    }

    /**
     * Take our list with movies and display them on our listview
     * Read more:
     * https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
     */
    public void displayMovies(List<Movie> movies) {
        if (movies == null) {
            TextView textView = (TextView) findViewById(R.id.sort_label);
            textView.setText("Sök igen");
        } else {
            DisplayResultsArrayAdapter arrayAdapter = new DisplayResultsArrayAdapter(this,
                    R.layout.display_results_list_item, movies, movieApi);
            setListAdapter(arrayAdapter);
        }
    }

    public void findMovies(String type, String title) {
        if (type.equals("discover")) {
			httpHandler.get(movieApi.createDiscoverMovieQuery(title), this);
		} else {
			httpHandler.get(movieApi.createMovieQuery(title), this);
		}

    }

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    /*public void sortMovies(View view) {
        // Sortera
        showToast("Sorterar!!!");
    }*/

    public void sortMovies(View view) {
        // Sortera
        showToast("Sorterar!");
        displayMovies(sortMethod.sort(movies));
    }

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	/**
	 * Handling intent data
	 */
	private void handleIntent(Intent intent) {
		String type, query;

		// Check if query comes from search field in activity bar
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			query = intent.getStringExtra(SearchManager.QUERY);
			type = "search";
		}

		// Otherwise it comes from navigation drawer browsing
		else {
			query = "latest"; // intent.getStringExtra("EXTRA_MESSAGE");
			type = "discover";
		}
		
		findMovies(type, query);
	}
}
