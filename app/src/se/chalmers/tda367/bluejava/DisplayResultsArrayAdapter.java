package se.chalmers.tda367.bluejava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DisplayResultsArrayAdapter extends ArrayAdapter<Movie> {

    private List<Movie> movies;
    private DisplayResultsActivity displayResultsActivity;
    private int textViewResourceId;

    public DisplayResultsArrayAdapter(DisplayResultsActivity displayResultsActivity,
                                      int textViewResourceId, List<Movie> movies) {
        super(displayResultsActivity, textViewResourceId, movies);
        this.displayResultsActivity = displayResultsActivity;
        this.textViewResourceId = textViewResourceId;
        this.movies = movies;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(textViewResourceId, null);
        }

        final Movie movie = movies.get(position);

        if (convertView != null) {
            TextView textView = (TextView) convertView.findViewById(R.id.movie_text);
            textView.setText(movie.toString());
        }

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(displayResultsActivity, DisplayMovieActivity.class);
                String message = movie.getID();
                intent.putExtra("key1", message);
                displayResultsActivity.startActivity(intent);
            }

        });

        return convertView;
    }
}
