package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.junit.*;
import java.io.FileNotFoundException;
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
    public void testSort() {
        try {
            Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/title_unsorted.txt"));
            while(sc.hasNextLine()) {
                unsorted += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        unsortedList = Movie.jsonToListOfMovies(unsorted);
        try {
            Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/title_sorted.txt"));
            while(sc.hasNextLine()) {
                correct += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        correctList = Movie.jsonToListOfMovies(correct);
        sortMethod = new SortByTitle();
        sortedList = sortMethod.sort(unsortedList);
        for(Movie movie : sortedList) {
            assertEquals(correctList, sortedList);
        }
    }


}
