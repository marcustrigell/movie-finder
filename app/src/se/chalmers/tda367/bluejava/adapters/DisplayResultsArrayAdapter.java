package se.chalmers.tda367.bluejava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayMovieActivity;
import se.chalmers.tda367.bluejava.activities.DisplayResultsActivity;

import java.util.List;

public class DisplayResultsArrayAdapter extends ArrayAdapter<Movie> {

    private final List<Movie> movies;

    private final DisplayResultsActivity displayResultsActivity;

    private final int textViewResourceId;

    private final MovieApi movieApi;

    public DisplayResultsArrayAdapter(DisplayResultsActivity displayResultsActivity,
                                      int textViewResourceId, List<Movie> movies, MovieApi movieApi) {

        super(displayResultsActivity, textViewResourceId, movies);

        this.displayResultsActivity = displayResultsActivity;

        this.textViewResourceId = textViewResourceId;

        this.movies = movies;

        this.movieApi = movieApi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(textViewResourceId, null);
        }

        final Movie movie = movies.get(position);

        if (convertView != null) {
            ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
            TextView taglineTextView = (TextView) convertView.findViewById(R.id.tagline);

            String url = movieApi.getThumbnailURL(movie.getPosterPath());
            Picasso.with(displayResultsActivity).load(url).into(coverImageView);

            titleTextView.setText(movie.toString());
            taglineTextView.setText("This movie is so awesome");

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(displayResultsActivity, DisplayMovieActivity.class);

                    intent.putExtra("movie", movie);
                    displayResultsActivity.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
