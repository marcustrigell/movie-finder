package se.chalmers.tda367.bluejava.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import se.chalmers.tda367.bluejava.helpers.AutoResizeTextView;
import se.chalmers.tda367.bluejava.interfaces.MovieFavoritesDB;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.MovieDetails;
import se.chalmers.tda367.bluejava.sqlite.MovieFavoritesDbHelper;

public class MovieDetailsTabFragment extends MovieTabFragment implements View.OnClickListener {

    private MovieFavoritesDB movieFavoritesDb;

    private boolean isFavorite;

    private Button favoriteButton;

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


    public static MovieDetailsTabFragment newInstance(Movie movie) {
        MovieDetailsTabFragment tab = new MovieDetailsTabFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);

        tab.setArguments(bundle);

        return tab;
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
        movieFavoritesDb = new MovieFavoritesDbHelper(context);
        isFavorite = movieFavoritesDb.isFavorite(movie.getID());
    }

    protected void getAdditionalInfo(int id) {
        if (movie.getDetails() == null) {
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
            final MovieDetails details = new MovieDetails(jsonObject);
            movie = new Movie.Builder(movie).details(details).build();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        populateView();
    }

    /**
     * Creates the fragment's view components
     */
    public void createView(View view) {
        favoriteButton = (Button) view.findViewById(R.id.favoriteButton);
        posterImageView = (ImageView) view.findViewById(R.id.posterImageView);

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

        ratingBar.setRating(Float.parseFloat(movie.getRating()) / 2);

        double popularityRounded = Double.parseDouble(movie.getPopularity());
        popularityRounded = Math.round(popularityRounded);

        titleTextView.setText(movie.getTitle());
        titleTextView.resizeText();

        if(!movie.getDetails().getTagline().equals("")) {
            tagLineTextView.setText(movie.getDetails().getTagline());
        } else {
            tagLineTextView.setText("No tagline");
        }
        tagLineTextView.resizeText();

        releaseYearTextView.setText(movie.getReleaseYear().substring(0,4));
        popularityTextView.setText("" + popularityRounded);
        overviewTextView.setText(movie.getDetails().getOverview());
        budgetTextView.setText("Budget: " + movie.getDetails().getBudget() + " $");
        revenueTextView.setText("Revenue: " + movie.getDetails().getRevenue() + " $");
        runTimeTextView.setText("Runtime: " + movie.getDetails().getRuntime() + " min");

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
                    movieFavoritesDb.deleteMovie(movie);
                    isFavorite = false;
                } else {
                    movieFavoritesDb.addMovie(movie);
                    isFavorite = true;
                }

                setFavoriteButtonState();

                break;

            case R.id.posterImageView:
                Intent intent = new Intent(getActivity(), DisplayPosterActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
                break;
        }
    }
}