package se.chalmers.tda367.bluejava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by marcus on 2014-03-26.
 *
 * Class for sorting Movie-objects by rating.
 *
 * @author Marcus Trigell
 *
 */
public class SortByYear implements ISort {

    /**
     * The method sorts the Movie-objects in ascending
     * order by release-year.
     * @param list The list to be sorted.
     */
    public void sort(List<Movie> list) {

        if(list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }

        // @see SortByTitle for more info about the following code.
        Map<Integer, Movie> map = new TreeMap<Integer, Movie>();

        for(Movie movie : list) {

            Integer releaseYear;
            try {
                releaseYear = Integer.parseInt(movie.getReleaseYear());
            } catch (NumberFormatException e) {
                releaseYear = 0;
            }

            map.put(releaseYear, movie);

        }

        list = new ArrayList<Movie>(map.values());
    }

}
