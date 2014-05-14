package se.chalmers.tda367.bluejava.activities;

import android.app.Activity;
import android.app.ListActivity;
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
    }

}
