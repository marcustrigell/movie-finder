package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DisplayMovieActivity extends Activity {

    private Movie movie;
    private MovieApi movieApi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_movie_activity);

        Intent intent = getIntent();
        movie = (Movie)intent.getParcelableExtra("key1");
        movieApi = new MovieApi();

        loadInfo();
    }

    public void loadInfo() {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        TextView releaseYearTextView = (TextView) findViewById(R.id.release_year);
        TextView ratingTextView = (TextView) findViewById(R.id.rating);
        TextView popularityTextView = (TextView) findViewById(R.id.popularity);

        //Inserting the image in the poster image view
        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        String url = movieApi.getThumbnailURL(movie.getPosterPath());
        Picasso.with(this).load(url).into(posterImageView);

        titleTextView.setText(movie.getTitle());
        descriptionTextView.setText("[Description of the movie]");
        releaseYearTextView.setText("Release year: " + movie.getReleaseYear());
        ratingTextView.setText("Rating: " + movie.getRating() + ", vote count: " + movie.getVoteCount());
        popularityTextView.setText("Popularity: " + movie.getPopularity());
    }
}
