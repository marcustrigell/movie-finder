package se.chalmers.tda367.bluejava.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.apis.HttpHandler;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.helpers.AutoResizeTextView;
import se.chalmers.tda367.bluejava.interfaces.JSONResultHandler;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Person;

/**
 * Used to display a detailed view of a person (actor or crew member).
 */
public class DisplayPersonActivity extends Activity implements JSONResultHandler {

    private Person person;
	private AndroidHttpClient httpClient;
	private HttpHandler httpHandler;
	private MovieApi movieApi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.display_person_activity);

		movieApi = new MovieApi();
		httpClient = HttpHandler.getAndroidHttpClient(this);
		httpHandler = new HttpHandler(httpClient);

        person = getIntent().getParcelableExtra(BlueJava.EXTRA_PERSON);

		getPersonDetails(person.getID());

    }

    /**
     * Sets the different attributes of the person to the view.
     */
    public void setupLayout() {
        if(person == null) {
            throw new RuntimeException("Error in DisplayPersonActivity, no person found");
        } else {
            /* Get the views that are to be changed. */
            ImageView imageView = (ImageView) findViewById(R.id.posterImageView);

            AutoResizeTextView nameView = (AutoResizeTextView) findViewById(R.id.person_name);
            AutoResizeTextView placeOfBirthView = (AutoResizeTextView) findViewById(R.id.placeOfBirth);
            AutoResizeTextView birthdayView = (AutoResizeTextView) findViewById(R.id.birthday);
            AutoResizeTextView deathdayView = (AutoResizeTextView) findViewById(R.id.deathday);
            AutoResizeTextView biographyView = (AutoResizeTextView) findViewById(R.id.biography);

            /* Finding the image url. */
            String url = movieApi.getCoverURL(person.getProfilePath());
            Picasso.with(BlueJava.getContext()).load(url).into(imageView);

            /* Assign the correct values to the views. */
            nameView.setText(person.getName());
            placeOfBirthView.setText(person.getPlaceOfBirth());
            birthdayView.setText(person.getBirthday());
            deathdayView.setText(person.getDeathday());
            biographyView.setText(person.getBiography());
        }
    }

    /**
     * Gets additional details about a person.
     * Details include information that wasn't requested in the first query.
     */
	public void getPersonDetails(int id) {
		httpHandler.get(movieApi.getPersonDetailsQuery(id), this);
	}

	@Override
	public void handleJSONResult(String json) {

		if (json == null) {
			return;
		}

		try {
			JSONObject jsonObject = new JSONObject(json);
			person = new Person(jsonObject);
		} catch (JSONException e) {
            throw new RuntimeException(e);
		}

		setupLayout();
	}


    /**
     * Launches a new activity with shows an image in full screen.
     * @param view The current view that the activity listens to
     */
    public void showFullScreen(View view) {
        Intent intent = new Intent(this, DisplayImageFullScreenActivity.class);
        intent.putExtra(BlueJava.EXTRA_PERSON, person);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		// Associate the searchable configuration with the SearchView by calling setSearchableInfo(SearchableInfo)

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle action bar actions click
		switch (item.getItemId()) {
			case R.id.action_settings:
				openSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when settings button is clicked.
	 * Starts SettingsActivity.
	 */
	public void openSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

}
