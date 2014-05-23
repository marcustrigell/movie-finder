package se.chalmers.tda367.bluejava.helpers;

import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for sorting results by rating.
 */
public class SortByRating implements SortMethod {

    /**
     * Sorts the list in ascending order by rating.
     * @param list The list to be sorted.
     * @return the sorted list.
     */
    @Override
    public List<Movie> sort(List<Movie> list) {

        if(list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }

        // @see SortByTitle for more info about the following code.
        Map<String, Movie> map = new TreeMap<String, Movie>();

        for(Movie movie : list) {

            String rating = movie.getRating();

            map.put(rating, movie);

        }

        return new ArrayList<Movie>(map.values());
    }
}
