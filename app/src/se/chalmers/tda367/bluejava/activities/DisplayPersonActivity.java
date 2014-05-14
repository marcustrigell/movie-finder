package se.chalmers.tda367.bluejava.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import se.chalmers.tda367.bluejava.R;
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
           /* The persons attributes is going to be set to the view here
            * when the getters and such in the class Person is implemented. */
        }
    }

}
