package se.chalmers.tda367.bluejava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by marcus on 2014-04-10.
 */
public class SortByPopularity implements SortMethod {

    /**
     *
     * @param list The list to be sorted.
     * @return
     */
    @Override
    public List<Movie> sort(List<Movie> list) {

        if(list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }

        // @see SortByTitle for more info about the following code.
        Map<String, Movie> map = new TreeMap<String, Movie>();

        for(Movie movie : list) {

            String popularity = movie.getPopularity();

            map.put(popularity, movie);

        }

        return new ArrayList<Movie>(map.values());
    }
}
