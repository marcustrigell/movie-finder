package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.junit.*;
import se.chalmers.tda367.bluejava.helpers.SortByTitle;
import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-07.
 */
public class SortByTitleTest extends TestCase {

    private List<Movie> sortedList, unsortedList, correctList;
    private SortMethod sortMethod;
    private String correct, unsorted;

    @Test
    public void testSort() throws Exception {
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/unsorted.txt"));
        while(sc.hasNextLine()) {
            unsorted += sc.nextLine();
        }
        unsortedList = Movie.jsonToListOfMovies(unsorted);
        sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/title_sorted.txt"));
        while(sc.hasNextLine()) {
            correct += sc.nextLine();
        }
        correctList = Movie.jsonToListOfMovies(correct);
        sortMethod = new SortByTitle();
        sortedList = sortMethod.sort(unsortedList);
        for(Movie movie : sortedList) {
            assertEquals(correctList, sortedList);
        }
    }
}
