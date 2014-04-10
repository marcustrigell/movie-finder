package se.chalmers.tda367.bluejava.test;

import junit.framework.TestCase;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import se.chalmers.tda367.bluejava.*;

/**
 * Created by marcus on 2014-04-02.
 */
public class MovieTest extends TestCase {

    private Movie movie;

	private String json = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"" +
			"/5vZw7ltCKI0JiOYTtRxaIC3DX0e.jpg\",\"id\":98,\"original_title\":\"Gladiator\"," +
			"\"release_date\":\"2000-05-01\",\"poster_path\":\"/6WBIzCgmDCYrqh64yDREGeDk9d3.jpg\"" +
			",\"popularity\":8.40221519445413,\"title\":\"Gladiator\",\"vote_average\":7.2,\"vote_count\"" +
			":1901}],\"total_pages\":1,\"total_results\": 1}";

    @Before
    public void initialize() {
        try {
            movie = new Movie(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	public void testToString() throws Exception {

    }

    @Test
    public void testGetID() throws Exception {
        String id = "98";
        assertEquals(id, movie.getID());
    }

    @Test
    public void testGetTitle() throws Exception {
        String title = "Gladiator";
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testGetReleaseYear() throws Exception {
        String releaseYear = "2000-05-01";
        assertEquals(releaseYear, movie.getReleaseYear());
    }

    @Test
    public void testGetPopularity() throws Exception {
        String popularity = "8.40221519445413";
        assertEquals(popularity, movie.getPopularity());
    }

    @Test
    public void testGetRating() throws Exception {
        String rating = "7.2";
        assertEquals(rating, movie.getRating());
    }

    @Test
    public void testGetVoteCount() throws Exception {
        String voteCount = "1901";
        assertEquals(voteCount, movie.getVoteCount());
    }

    public void testGetPosterPath() throws Exception {

    }

    public void testJsonToListOfMovies() throws Exception {

    }

    public void testDescribeContents() throws Exception {

    }

    public void testWriteToParcel() throws Exception {

    }
}
