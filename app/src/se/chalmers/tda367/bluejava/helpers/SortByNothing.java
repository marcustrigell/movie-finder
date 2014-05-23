package se.chalmers.tda367.bluejava.helpers;

import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * Class for when no sorting is to be done.
 */
public class SortByNothing implements SortMethod {

    /**
     * Returns the list as is.
     * This class is used when no sorting order has been chosen
     * and user still clicks on sort button.
     * @param list The list to be sorted.
     * @return the same list.
     */
    @Override
    public List<Movie> sort(List<Movie> list) {
        return list;
    }
}
