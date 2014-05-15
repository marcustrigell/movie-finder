package se.chalmers.tda367.bluejava.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.helpers.AutoResizeTextView;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.Person;

/**
 * Used to display a detailed view of a person.
 *
 * Created by marcus on 2014-05-13.
 */
public class DisplayPersonActivity extends Activity {

    private Person person;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.display_person_activity);

        Intent intent = getIntent();

        person = intent.getParcelableExtra("person");

        setupLayout();
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
            AutoResizeTextView homepageView = (AutoResizeTextView) findViewById(R.id.homepage);
            AutoResizeTextView biographyView = (AutoResizeTextView) findViewById(R.id.biography);
            ListView appearences = (ListView) findViewById(R.id.appearences_list);

            /* Finding the image url. */
            MovieApi movieApi = new MovieApi();
            String url = movieApi.getCoverURL(person.getProfilePath());
            Picasso.with(BlueJava.getContext()).load(url).into(imageView);

            /* Assign the correct values to the views. */
            nameView.setText(person.getName());
            /*placeOfBirthView.setText(person.getPlaceOfBirth());*/ //TODO doesn't work until person is complete
            /*birthdayView.setText(person.getBirthday());*/ //TODO
            /*deathdayView.setText(person.getDeathday());*/ //TODO
            /*homepageView.setText(person.getHomepage());*/ //TODO
            /*biographyView.setText(person.getBiography());*/ //TODO

            /* Set the listviews arrayadapter. */
            /*ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<Movie>
                    (this, android.R.layout.simple_list_item_1, person.getMovies());*/ //TODO check array parameters
            /*appearences.setAdapter(arrayAdapter);*/ //TODO

        }
    }

}
