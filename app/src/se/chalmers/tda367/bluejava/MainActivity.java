package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "se.chalmers.tda367.bluejava.MESSAGE";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	public void searchMovies(View view) {
        Intent intent = new Intent(this, DisplayResultsActivity.class);
        EditText searchField = (EditText) findViewById(R.id.search_field);

		try {
			String message = searchField.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, message);

		} catch (NullPointerException e) {

		}

        startActivity(intent);
	}
}
