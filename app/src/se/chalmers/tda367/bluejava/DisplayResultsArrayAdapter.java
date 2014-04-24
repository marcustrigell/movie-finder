package se.chalmers.tda367.bluejava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DisplayResultsArrayAdapter extends ArrayAdapter<Movie> {

    private List<Movie> movies;
    private DisplayResultsActivity displayResultsActivity;
    private int textViewResourceId;
    private MovieApi movieApi;

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
                    String message = movie.getID();
                    intent.putExtra("key1", movie);
                    displayResultsActivity.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
