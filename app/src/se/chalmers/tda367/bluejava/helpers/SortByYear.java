package se.chalmers.tda367.bluejava.helpers;

import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for sorting Movie-objects by rating.
 */
public class SortByYear implements SortMethod {

    /**
     * The method sorts the Movie-objects in ascending
     * order by release-date.
     * @param list The list to be sorted.
     */
    @Override
    public List<Movie> sort(List<Movie> list) {

        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }

        // @see SortByTitle for more info about the following code.
        Map<String, Movie> map = new TreeMap<String, Movie>();

        for (Movie movie : list) {

            String releaseYear = movie.getReleaseYear();

            map.put(releaseYear, movie);
        }

        return new ArrayList<Movie>(map.values());
    }

}
