package se.chalmers.tda367.bluejava;

import java.util.ArrayList;
import java.util.List;
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
     * Method for sorting.
     * @param list
     */
    public void sort(List<Movie> list) {

        if(list == null || list.size() == 0) {
            return; // TODO exception?
        }
        /* Create a TreeMap for sorting the movies by title */
        TreeMap<String, Movie> treemap = new TreeMap<String, Movie>();

        for (Movie movie : list) {

            String title = movie.getTitle();

            /*
            * Using the title as key will naturally sort
            * the map by title.
            */
            treemap.put(title, movie);
        }
        /* Convert the Map to a List */
        list = new ArrayList<Movie>(treemap.values());
    }

}
