package se.chalmers.tda367.bluejava.models;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import se.chalmers.tda367.bluejava.tests.R;

import java.util.List;

/**
 * Created by axelniklasson on 2014-05-12.
 */
public class MovieTest extends InstrumentationTestCase {

    private Movie movie, movieFromParcel;
    private final int ID = 0;
    private final String TITLE = "";
    private final String RELEASE_YEAR = "";
    private final String POPULARITY = "";
    private final String RATING = "";
    private final String VOTE_COUNT = "";
    private final String POSTER_PATH = "";
    private final String IMDB_ID = "";
    private final String BUDGET = "";
    private final String OVERVIEW = "";
    private final String TAGLINE = "";
    private final String RUNTIME = "";
    private final String REVENUE = "";
    private List<Genre> genres;
    private Credits CREDITS;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.movie); //TODO: save correct info in movie.xml
        movie = new Movie.Builder(new JSONObject(json)).build();
        Parcel parcel = Parcel.obtain();
        movie.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        movieFromParcel = (Movie)Movie.CREATOR.createFromParcel(parcel); //TODO: casting needed?
    }

    @Test
    public void testParcelable() {
        assertEquals(movie.describeContents(), movieFromParcel.describeContents());
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(ID, movie.getID());
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals(TITLE, movie.getTitle());
    }

    @Test
    public void testGetReleaseYear() throws Exception {
        assertEquals(RELEASE_YEAR, movie.getReleaseYear());
    }

    @Test
    public void testGetPopularity() throws Exception {
        assertEquals(POPULARITY, movie.getPopularity());
    }

    @Test
    public void testGetRating() throws Exception {
        assertEquals(RATING, movie.getRating());
    }

    @Test
    public void testGetVoteCount() throws Exception {
        assertEquals(VOTE_COUNT, movie.getVoteCount());
    }

    @Test
    public void testGetPosterPath() throws Exception {
        assertEquals(POSTER_PATH, movie.getPosterPath());
    }

    @Test
    public void testGetGenres() throws Exception {
        //TODO: implement
    }

    @Test
    public void testGetImdbID() throws Exception {
        assertEquals(IMDB_ID, movie.getImdbID());
    }

    @Test
    public void testGetBudget() throws Exception {
        assertEquals(BUDGET, movie.getBudget());
    }

    @Test
    public void testGetOverview() throws Exception {
        assertEquals(OVERVIEW, movie.getOverview());
    }

    @Test
    public void testGetTagline() throws Exception {
        assertEquals(TAGLINE, movie.getTagline());
    }

    @Test
    public void testGetRuntime() throws Exception {
        assertEquals(TAGLINE, movie.getTagline());
    }

    @Test
    public void testGetRevenue() throws Exception {
        assertEquals(REVENUE, movie.getRevenue());
    }

    @Test
    public void testGetCredits() throws Exception {
        //TODO: implement
    }
}
