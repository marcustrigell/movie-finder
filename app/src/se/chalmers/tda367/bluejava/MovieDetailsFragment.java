package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsFragment extends MovieFragment implements View.OnClickListener {

    public MovieDetailsFragment(Activity activity, Movie movie) {
        super(activity, movie, R.layout.fragment_movie_details);
    }

    /**
     * Get more info about our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    @Override
    protected void getAdditionalInfo(String id) {
        httpHandler.get(movieApi.getMovieDetailsQuery(id), this);
    }

    /**
     * Handles the callback from the API
     *
     * @param json The JSON result from the API
     */
    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            movie = new Movie.Builder(movie).details(jsonObject).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setupLayout();
    }

    /**
     * Builds the screen's layout
     */
    public void setupLayout() {

        // Set the trailer-buttons listener
        Button button = (Button) getView().findViewById(R.id.trailer);
        button.setOnClickListener(this);

        //Finding the fields that is to be set to values
        ImageView posterImageView = (ImageView) getView().findViewById(R.id.posterImageView);
        AutoResizeTextView titleTextView = (AutoResizeTextView) getView().findViewById(R.id.title);
        AutoResizeTextView tagLineTextView = (AutoResizeTextView) getView().findViewById(R.id.tagline);
        AutoResizeTextView releaseYearTextView = (AutoResizeTextView) getView().findViewById(R.id.release_year);
        AutoResizeTextView popularityTextView = (AutoResizeTextView) getView().findViewById(R.id.popularity);
        AutoResizeTextView overviewTextView = (AutoResizeTextView) getView().findViewById(R.id.overview);
        RatingBar ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar);

        //Inserting the image in the poster image view
        String url = movieApi.getCoverURL(movie.getPosterPath());
        Picasso.with(BlueJava.getContext()).load(url).into(posterImageView);

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
        overviewTextView.setText(movie.getOverview());
        overviewTextView.setMovementMethod(new ScrollingMovementMethod());
    }


    @Override
    public void onClick(View view) {
        String youtubeAddr = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAddr)));
    }
}