package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DisplayMovieActivity extends Activity implements JSONResultHandler {

    private AndroidHttpClient httpClient;

    private HttpHandler httpHandler;

    private MovieApi movieApi;

    private Movie movie;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_movie_activity);

        movieApi = new MovieApi();

        httpClient = HttpHandler.getAndroidHttpClient(this);

        httpHandler = new HttpHandler(httpClient);

        Intent intent = getIntent();

        movie = intent.getParcelableExtra("key1");

        getMovieDetails(movie.getID());

        loadInfo();
    }

    /**
     * Get all info about our movie
     */
    private void getMovieDetails(String id) {
        httpHandler.get(movieApi.getMovieDetailsQuery(id), this);
    }

    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        /*movie.addDetails(json);*/
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
