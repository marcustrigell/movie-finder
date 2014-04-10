package se.chalmers.tda367.bluejava.test;

import junit.framework.TestCase;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.ISort;
import se.chalmers.tda367.bluejava.Movie;
import se.chalmers.tda367.bluejava.SortByTitle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;

import java.util.List;

/**
 * Created by marcus on 2014-03-27.
 *
 * Test-class for SortByTitle
 *
 * @author Marcus Trigell
 *
 */
public class SortByTitleTest extends TestCase {

    public void testSort() throws Exception {

        /*
         * Creating a json String to create a List of movies from.
         */
        Scanner in = new Scanner(new FileReader("json.txt"));
        String json = "";
        while(in.hasNext()) {
            json = json + "\n" + in.nextLine();
        }

        List<Movie> list = Movie.jsonToListOfMovies(json);

        /*
         * Create a list of the movie titles to use when comparing later.
         */
        List<String> titles = new ArrayList<String>();
        for(Movie movie : list) {
            titles.add(movie.getTitle());
        }

        ISort sortMethod = new SortByTitle();
        list = sortMethod.sort(list);

        /*
         * Check if lists are sorted by titles by comparing the title-list
         * and the now sorted Movie-list.
         */
        int index = 0;
        for(Movie movie : list) {
            assertTrue(!(movie.getTitle().equals(titles.get(index))));
        }

    }
}
