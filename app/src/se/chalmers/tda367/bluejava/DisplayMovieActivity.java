package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        //Finding the fields that is to be set to values
        TextView titleTextView = (TextView) findViewById(R.id.title);
        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        TextView releaseYearTextView = (TextView) findViewById(R.id.release_year);
        TextView popularityTextView = (TextView) findViewById(R.id.popularity);

        //Inserting the image in the poster image view
        String url = movieApi.getThumbnailURL(movie.getPosterPath());
        Picasso.with(this).load(url).into(posterImageView);

        //Setting the rating to the rating bar
        ratingBar.setRating(Float.parseFloat(movie.getRating()) / 2);

        //Rounding the popularity
        double popularityRounded = Double.parseDouble(movie.getPopularity());
        popularityRounded = Math.round(popularityRounded);

        //Setting the strings to values
        titleTextView.setText(movie.getTitle());
        descriptionTextView.setText("[Tagline]");
        releaseYearTextView.setText("Release year: " + movie.getReleaseYear());
        popularityTextView.setText("Popularity: " + popularityRounded);
    }
}
