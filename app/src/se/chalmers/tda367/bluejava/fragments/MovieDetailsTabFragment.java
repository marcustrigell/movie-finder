package se.chalmers.tda367.bluejava.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayPosterActivity;
import se.chalmers.tda367.bluejava.sqlite.MovieFavoritesDbHelper;
import se.chalmers.tda367.bluejava.helpers.AutoResizeTextView;
import se.chalmers.tda367.bluejava.models.Movie;

public class MovieDetailsTabFragment extends MovieTabFragment implements View.OnClickListener {

    private MovieFavoritesDbHelper movieFavoritesDbHelper;

    private boolean isFavorite;

    private Button favoriteButton;

    private Button trailerButton;

    private ImageView posterImageView;

    private AutoResizeTextView titleTextView;

    private AutoResizeTextView tagLineTextView;

    private AutoResizeTextView releaseYearTextView;

    private AutoResizeTextView popularityTextView;

    private AutoResizeTextView overviewTextView;

    private RatingBar ratingBar;

    private AutoResizeTextView budgetTextView;

    private AutoResizeTextView revenueTextView;

    private AutoResizeTextView runTimeTextView;

    public MovieDetailsTabFragment(Movie movie) {
        super(movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_movie_details, container, false);
        createView(view);
        return view;
    }

    @Override
    public void init() {
        super.init();
        movieFavoritesDbHelper = new MovieFavoritesDbHelper(context);
        isFavorite = movieFavoritesDbHelper.isFavorite(movie.getID());
    }

    @Override
    public void onResume() {
        super.onResume();
        getAdditionalInfo(movie.getID());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {

            if (!isVisibleToUser) {
                //Log.e("MyFragment", "Details NOT visible.");
            }
        }
    }

    @Override
    protected void getAdditionalInfo(int id) {
        if (movie.getBudget() == null) {
            Log.e("", "get details");
            httpHandler.get(movieApi.getMovieDetailsQuery(id), this);
        } else {
            populateView();
        }
    }

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

        populateView();
    }

    /**
     * Creates the fragment's view components
     */
    public void createView(View view) {
        trailerButton = (Button) view.findViewById(R.id.trailerButton);
        favoriteButton = (Button) view.findViewById(R.id.favoriteButton);
        posterImageView = (ImageView) view.findViewById(R.id.posterImageView);

        trailerButton.setOnClickListener(this);
        favoriteButton.setOnClickListener(this);
        posterImageView.setOnClickListener(this);

        titleTextView = (AutoResizeTextView) view.findViewById(R.id.title);
        tagLineTextView = (AutoResizeTextView) view.findViewById(R.id.tagline);
        releaseYearTextView = (AutoResizeTextView) view.findViewById(R.id.release_year);
        popularityTextView = (AutoResizeTextView) view.findViewById(R.id.popularity);
        overviewTextView = (AutoResizeTextView) view.findViewById(R.id.overview);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        budgetTextView = (AutoResizeTextView) view.findViewById(R.id.budget);
        revenueTextView = (AutoResizeTextView) view.findViewById(R.id.revenue);
        runTimeTextView = (AutoResizeTextView) view.findViewById(R.id.runtime);
    }

    /**
     * Populates the fragment's view with movie information
     */
    public void populateView() {

        //Inserting the image in the poster image view
        String url = movieApi.getCoverURL(movie.getPosterPath());
        Picasso.with(context).load(url).into(posterImageView);

        //Setting the rating to the rating bar
        ratingBar.setRating(Float.parseFloat(movie.getRating()) / 2);

        //Rounding the popularity
        double popularityRounded = Double.parseDouble(movie.getPopularity());
        popularityRounded = Math.round(popularityRounded);

        //Setting the strings to values
        titleTextView.setText(movie.getTitle());
        titleTextView.resizeText();

        if(!movie.getTagline().equals("")) {
            tagLineTextView.setText(movie.getTagline());
        } else {
            tagLineTextView.setText("No tagline");
        }
        tagLineTextView.resizeText();

        releaseYearTextView.setText(movie.getReleaseYear().substring(0,4));
        popularityTextView.setText("" + popularityRounded);
        overviewTextView.setText(movie.getOverview());
        budgetTextView.setText("Budget: " + movie.getBudget() + " $");
        revenueTextView.setText("Revenue: " + movie.getRevenue() + " $");
        runTimeTextView.setText("Runtime: " + movie.getRuntime() + " min");

        setFavoriteButtonState();
    }

    private void setFavoriteButtonState() {
        if (isFavorite) {
            favoriteButton.setBackgroundResource(R.drawable.star);
        } else {
            favoriteButton.setBackgroundResource(R.drawable.star_off);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.favoriteButton:

                if (isFavorite) {
                    movieFavoritesDbHelper.deleteMovie(movie);
                    isFavorite = false;
                } else {
                    movieFavoritesDbHelper.addMovie(movie);
                    isFavorite = true;
                }

                setFavoriteButtonState();

                break;

            case R.id.posterImageView:
                Intent intent = new Intent(getActivity(), DisplayPosterActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
                break;

            case R.id.trailerButton:

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
                break;
        }
    }

    /**
     * Get videos of our movie
     *
     * @param id The ID of the movie we want to add info to
     */
    protected void getMovieVideos(int id) {
        httpHandler.get(movieApi.getMovieVideosQuery(id), this);
    }
}