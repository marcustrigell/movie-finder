package se.chalmers.tda367.bluejava.helpers;

import android.content.Context;
import android.test.InstrumentationTestCase;
import org.junit.Test;
import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.tests.R;

import java.util.List;

public class SortByPopularityTest extends InstrumentationTestCase {

    private List<Movie> sortedList, unsortedList, correctList;
    private SortMethod sortMethod;
    private String sorted, unsorted;

    @Override
    public void setUp() throws Exception{
        super.setUp();
        Context context = getInstrumentation().getContext();
        unsorted = context.getString(R.string.unsorted);
        sorted = context.getString(R.string.popularity_sorted);
    }

    @Test
    public void testSort() throws Exception {
        unsortedList = Movie.jsonToListOfMovies(unsorted);
        correctList = Movie.jsonToListOfMovies(sorted);

        sortMethod = new SortByPopularity();
        sortedList = sortMethod.sort(unsortedList);

        for(int i = 0; i < correctList.size(); i++) {
            assertEquals(correctList.get(i).getPopularity(), sortedList.get(i).getPopularity());
        }
    }
}