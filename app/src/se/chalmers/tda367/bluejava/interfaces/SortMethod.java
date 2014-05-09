package se.chalmers.tda367.bluejava.interfaces;

import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * Created by marcus on 2014-03-25.
 *
 * Interface for sorting hits in the
 * DisplayResultsActivity-class.
 *
 * @author Marcus Trigell
 *
 */
public interface SortMethod {

    /**
     * Method for sorting a list of Movie-objects.
     *
     * @param list The list to be sorted.
     *
     */
    public List<Movie> sort(List<Movie> list);

}
