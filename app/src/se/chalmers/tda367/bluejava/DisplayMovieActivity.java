package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMovieActivity extends Activity {

    private Movie movie;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_movie_activity);

        Intent intent = getIntent();
        movie = (Movie)intent.getParcelableExtra("key1");

        loadInfo();
    }

    public void loadInfo() {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        TextView ratingTextView = (TextView) findViewById(R.id.rating);
        TextView voteCountTextView = (TextView) findViewById(R.id.vote_count);

        titleTextView.setText(movie.getTitle());
        descriptionTextView.setText("[Description of the movie");
        ratingTextView.setText("[Rating of the movie]");
        voteCountTextView.setText("[Vote count of the movie");
    }
}
