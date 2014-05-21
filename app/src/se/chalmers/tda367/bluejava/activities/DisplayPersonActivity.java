package se.chalmers.tda367.bluejava.activities;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
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
 * Used to display a detailed view of a person.
 *
 * Created by marcus on 2014-05-13.
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

        person = getIntent().getParcelableExtra("person");

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
            ListView appearences = (ListView) findViewById(R.id.appearences_list);

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
			e.printStackTrace();
		}

		setupLayout();
	}
}
