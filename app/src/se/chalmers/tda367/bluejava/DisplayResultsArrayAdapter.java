package se.chalmers.tda367.bluejava;

import android.widget.ArrayAdapter;

import java.util.List;

public class DisplayResultsArrayAdapter extends ArrayAdapter<Movie> {

    private List<Movie> movies;
    private DisplayResultsActivity displayResultsActivity;

    public DisplayResultsArrayAdapter(DisplayResultsActivity displayResultsActivity,
                                      int textViewResourceId, List<Movie> movies) {
        super(displayResultsActivity, textViewResourceId, movies);
        this.movies = movies;
        this.displayResultsActivity = displayResultsActivity;
    }
}
