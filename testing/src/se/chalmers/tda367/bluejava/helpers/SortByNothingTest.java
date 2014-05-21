package se.chalmers.tda367.bluejava.helpers;

import junit.framework.TestCase;
import org.junit.Test;
import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class SortByNothingTest extends TestCase {

    private List<Movie> sortedList, unsortedList, correctList;
    private SortMethod sortMethod;
    private String correct, unsorted;

    @Test
    public void testSort() throws Exception {
        Scanner sc = new Scanner(new FileReader("/res/txts/unsorted.txt"));
        while(sc.hasNextLine()) {
            unsorted += sc.nextLine();
        }
        unsortedList = Movie.jsonToListOfMovies(unsorted);
        sc = new Scanner(new FileReader("/res/txts/unsorted.txt"));
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