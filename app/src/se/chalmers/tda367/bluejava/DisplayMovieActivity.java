package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMovieActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_movie_activity);

        Intent intent = getIntent();
        Movie movie = (Movie)intent.getParcelableExtra("key1");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(movie.getTitle());
    }
}
