package se.chalmers.tda367.bluejava.interfaces;

import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * Interface for sorting hits in the
 * DisplayResultsActivity-class.
 */
public interface SortMethod {

    /**
     * Method for sorting a list of Movie-objects.
     *
     * @param list The list to be sorted.
     */
    public List<Movie> sort(List<Movie> list);

}
