package se.chalmers.tda367.bluejava.activities;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.DisplayResultsArrayAdapter;
import se.chalmers.tda367.bluejava.apis.HttpHandler;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.helpers.*;
import se.chalmers.tda367.bluejava.interfaces.JSONResultHandler;
import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.Collections;
import java.util.List;

public class DisplayResultsActivity extends ListActivity
        implements JSONResultHandler, AdapterView.OnItemSelectedListener {

    private AndroidHttpClient httpClient;

    private HttpHandler httpHandler;

    private MovieApi movieApi;

    private List<Movie> movies;
	private List<Actor> people;
	private List<?> results;

    private SortMethod sortMethod;

    private boolean movieListAscending;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_results_activity);

        Spinner spinner = (Spinner) findViewById(R.id.sort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
        sortMethod = new SortByNothing();

        /* Set the order to sort the movie list */
        movieListAscending = true;

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

    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        } else if (json.contains("profile_path")) {

            Log.i("JSON", json);
			try {
				JSONObject jsonObject = new JSONObject(json);
				people = Actor.jsonToListOfActors(jsonObject);

				String toastMessage = (people.get(0) instanceof Actor)
						? "An Actor found!!"
						: "Sorry! You must be a united fan.";
				showToast(toastMessage);

				displayResults(people);
			} catch (JSONException e) {
			}
		} else {

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

        displayResults(movies);

		}
    }

    /**
     * Take our list with movies and display them on our listview
     * Read more:
     * https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
     */
    public void displayResults(List<?> results) {
        if (results == null) {
            showToast("No Hits\n" + "Search again");
            // This doesn't work anymore when spinner was added instead of textfield
/*            TextView textView = (TextView) findViewById(R.id.sort_label);
            textView.setText("Sök igen");*/
        } else {
            DisplayResultsArrayAdapter arrayAdapter = new DisplayResultsArrayAdapter(this,
                    R.layout.display_results_list_item, results, movieApi);
            setListAdapter(arrayAdapter);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    public void sortMovies(View view) {
        if(sortMethod instanceof SortByNothing) {
            showToast("Choose sort method");
        } else {
            showToast("Sorting");
            if(movieListAscending) {
                displayResults(sortMethod.sort(movies));
            } else {
                List<Movie> tmp = sortMethod.sort(movies);
                Collections.reverse(tmp);
                displayResults(tmp);
            }
        }
    }

    public void changeSortOrder(View view) {
        Button button = (Button) view.findViewById(R.id.ascending);
        if(movieListAscending) {
            movieListAscending = false;
            button.setText("Descending");

        } else {
            movieListAscending = true;
            button.setText("Ascending");
        }
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
        String query;

		// Check if query comes from search field in activity bar
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			query = intent.getStringExtra(SearchManager.QUERY);
		}

		// Otherwise it comes from navigation drawer browsing
		else {
            query = intent.getStringExtra("se.chalmers.tda367.bluejava.MESSAGE");
		}

		sendQuery(query);
	}

    public void sendQuery(String query) {
        if (query.equals("upcoming")) {
            httpHandler.get(movieApi.getUpcomingMoviesQuery(), this);
        } else if (query.equals("popular")) {
            httpHandler.get(movieApi.getPopularMoviesQuery(), this);
        }  else if (query.equals("top_rated")) {
            httpHandler.get(movieApi.getTopRatedMoviesQuery(), this);
		} else if (query.equals("search_people")) {
			httpHandler.get(movieApi.getSearchPeopleQuery("Brad Pitt"), this); //TODO should not be "Brad Pitt"
		} else {
            httpHandler.get(movieApi.getSearchMovieQuery(query), this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (pos == 1) {
            sortMethod = new SortByTitle();
        } else if (pos == 2) {
            sortMethod = new SortByYear();
        } else if (pos == 3) {
            sortMethod = new SortByRating();
        } else if (pos == 4) {
            sortMethod = new SortByVoteCount();
        } else if (pos == 5) {
            sortMethod = new SortByPopularity();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        ;
    }
}
