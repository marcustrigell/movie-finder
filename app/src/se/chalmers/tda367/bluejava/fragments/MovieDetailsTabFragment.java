package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayPosterActivity;
import se.chalmers.tda367.bluejava.helpers.AutoResizeTextView;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;

public class MovieDetailsTabFragment extends MovieTabFragment implements View.OnClickListener {

    public MovieDetailsTabFragment(Activity activity, Movie movie) {
        super(activity, movie, R.layout.fragment_movie_details);
    }

    /**
     * Get more info about our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    @Override
    protected void getAdditionalInfo(int id) {
        httpHandler.get(movieApi.getMovieDetailsQuery(id), this);
    }

    /**
     * Get videos of our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    @Override
    protected void getMovieVideos(int id) {
        httpHandler.get(movieApi.getMovieVideosQuery(id), this);
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
            // Check if the json-string is a details or video-query.
            if(jsonObject.has("key")) {
                movie = new Movie.Builder(movie).videos(jsonObject).build();
            } else {
                movie = new Movie.Builder(movie).details(jsonObject).build();
            }
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
        posterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayPosterActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });
        AutoResizeTextView titleTextView = (AutoResizeTextView) getView().findViewById(R.id.title);
        AutoResizeTextView tagLineTextView = (AutoResizeTextView) getView().findViewById(R.id.tagline);
        AutoResizeTextView releaseYearTextView = (AutoResizeTextView) getView().findViewById(R.id.release_year);
        AutoResizeTextView popularityTextView = (AutoResizeTextView) getView().findViewById(R.id.popularity);
        AutoResizeTextView overviewTextView = (AutoResizeTextView) getView().findViewById(R.id.overview);
        RatingBar ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar);
        AutoResizeTextView budgetTextView = (AutoResizeTextView) getView().findViewById(R.id.budget);
        AutoResizeTextView revenueTextView = (AutoResizeTextView) getView().findViewById(R.id.revenue);
        AutoResizeTextView runTimeTextView = (AutoResizeTextView) getView().findViewById(R.id.runtime);

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
        titleTextView.resizeText();
        tagLineTextView.setText(movie.getTagline());
        tagLineTextView.resizeText();
        releaseYearTextView.setText(movie.getReleaseYear().substring(0,4));
        popularityTextView.setText("" + popularityRounded);
        overviewTextView.setText(movie.getOverview());
        budgetTextView.setText("Budget: " + movie.getBudget() + " $");
        revenueTextView.setText("Revenue: " + movie.getRevenue() + " $");
        runTimeTextView.setText("Runtime: " + movie.getRuntime() + " min");
    }


    @Override
    public void onClick(View view) {
//        List<Video> videos = movie.getVideos();
//
//        int position = 0;
//        boolean noTrailer = true;
//        Video video;
//        String youtubeID = "_O1hM-k3aUY";
//        while(position < videos.size() || noTrailer) {
//            video = videos.get(position);
//            if(video.getType().equals("Trailer")) {
//                youtubeID = videos.get(position).getKey();
//                noTrailer = false;
//            } else {
//                position++;
//            }
//        }
//        String youtubeAddr = movieApi.getYoutubeURL(youtubeID);
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAddr)));

    }
}