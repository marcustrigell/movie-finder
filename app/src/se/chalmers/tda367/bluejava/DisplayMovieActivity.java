package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayMovieActivity extends Activity implements JSONResultHandler, View.OnClickListener {

    private static final String TAG = "DisplayMovieActivity";

    private AndroidHttpClient httpClient;

    private HttpHandler httpHandler;

    private MovieApi movieApi;

    private Movie movie;

    private String youtubeAddr;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_movie_activity);

        // Set the trailer-buttons listener
        Button button = (Button) findViewById(R.id.trailer);
        button.setOnClickListener(this);

        movieApi = new MovieApi();

        httpClient = HttpHandler.getAndroidHttpClient(this);

        httpHandler = new HttpHandler(httpClient);

        Intent intent = getIntent();

        movie = intent.getParcelableExtra("key1");

        // This should be changed to a address which is stored in the movie object
        youtubeAddr = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

        getMovieDetails(movie.getID());
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

        try {
            JSONObject jsonObject = new JSONObject(json);
            movie = new Movie.Builder(jsonObject).details(jsonObject).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        loadInfo();
    }

    public void loadInfo() {
        //Finding the fields that is to be set to values
        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        TextView titleTextView = (TextView) findViewById(R.id.title);
        TextView tagLineTextView = (TextView) findViewById(R.id.tagline);
        TextView releaseYearTextView = (TextView) findViewById(R.id.release_year);
        TextView popularityTextView = (TextView) findViewById(R.id.popularity);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

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

        tagLineTextView.setText(movie.getTagline());
        releaseYearTextView.setText(movie.getReleaseYear().substring(0,4));
        popularityTextView.setText("" + popularityRounded);
    }

    // Used when user clicks on movie trailer button
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAddr)));
    }
}
