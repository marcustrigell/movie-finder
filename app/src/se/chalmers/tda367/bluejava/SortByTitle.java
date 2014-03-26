package se.chalmers.tda367.bluejava;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by marcus on 2014-03-25.
 *
 * Class for sorting a list of Movie-objects by title.
 *
 * @author Marcus Trigell
 *
 */
public class SortByTitle implements ISort {

    /**
     * Method for sorting a list of Movie-objects.
     * The method sorts the objects in ascending alphabetical
     * order by the movies title.
     * @param list The list to be sorted.
     */
    public List<Movie> sort(List<Movie> list) {

        if(list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }

        /* Create a TreeMap for sorting the movies by title */
        Map<String, Movie> map = new TreeMap<String, Movie>();

        for (Movie movie : list) {

            String title = movie.getTitle();

            /*
            * Using the title as key will naturally sort
            * the following Collection by title when
            * we call map.values later.
            */
            map.put(title, movie);
        }

        /* Convert the Map to a List (Collection) */
        list = new ArrayList<Movie>(map.values());  //TODO

        //return list;
    }

}
