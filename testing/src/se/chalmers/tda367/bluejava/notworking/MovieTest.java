package se.chalmers.tda367.bluejava.notworking;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.Genre;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.MovieDetails;
import se.chalmers.tda367.bluejava.tests.R;

import java.util.List;

/**
 * Created by axelniklasson on 2014-05-12.
 */
public class MovieTest extends InstrumentationTestCase {

    private Movie movie, movieFromParcel;
    private final int ID = 550;
    private final String TITLE = "Fight Club";
    private final String RELEASE_YEAR = "1999-10-15";
    private final String POPULARITY = "61151.745000000003";
    private final String RATING = "9.0999999999999996";
    private final String VOTE_COUNT = "174";
    private final String POSTER_PATH = "/2lECpi35Hnbpa4y46JX0aY3AWTy.jpg";
    private final String IMDB_ID = "tt0137523";
    private final String BUDGET = "63000000";
    private final String OVERVIEW = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male " +
            "aggression into a shocking new form of therapy. Their concept catches on, with underground " +
            "\\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites " +
            "an out-of-control spiral toward oblivion.";
    private final String TAGLINE = "How much can you know about yourself if you've never been in a fight?";
    private final String RUNTIME = "139";
    private final String REVENUE = "100853753";
    private List<Genre> genres;
    private Credits credits;

    private String basicJSON, detailsJSON, creditsJSON, genresJSON;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();

        basicJSON = context.getString(R.string.movie_basic);
        detailsJSON = context.getString(R.string.movie_details);
        creditsJSON = context.getString(R.string.credits);
        genresJSON = context.getString(R.string.genres);

        MovieDetails movieDetails = new MovieDetails(new JSONObject(detailsJSON));

        Movie.Builder builder = new Movie.Builder(new JSONObject(basicJSON));
        movie = builder.details(movieDetails).credits(new Credits(new JSONObject(creditsJSON))).build();

        Parcel parcel = Parcel.obtain();
        movie.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        movieFromParcel = (Movie)Movie.CREATOR.createFromParcel(parcel); //TODO: should casting really be needed?
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
        genres = Genre.jsonToListOfGenres(new JSONArray(genresJSON));
        for(int i = 0; i < genres.size(); i++) {
            assertEquals(genres.get(i), movie.getDetails().getGenres().get(i));
        }
    }

    @Test
    public void testGetImdbID() throws Exception {
        assertEquals(IMDB_ID, movie.getDetails().getImdbID());
    }

    @Test
    public void testGetBudget() throws Exception {
        assertEquals(BUDGET, movie.getDetails().getBudget());
    }

    @Test
    public void testGetOverview() throws Exception {
        assertEquals(OVERVIEW, movie.getDetails().getOverview());
    }

    @Test
    public void testGetTagline() throws Exception {
        assertEquals(TAGLINE, movie.getDetails().getTagline());
    }

    @Test
    public void testGetRuntime() throws Exception {
        assertEquals(RUNTIME, movie.getDetails().getRuntime());
    }

    @Test
    public void testGetRevenue() throws Exception {
        assertEquals(REVENUE, movie.getDetails().getRevenue());
    }

    @Test
    public void testGetCredits() throws Exception {
        credits = new Credits(new JSONObject(creditsJSON));
        assertEquals(credits, movie.getCredits());
    }

    //TODO: implement test for Movie.jsonToListOfMovies
}
