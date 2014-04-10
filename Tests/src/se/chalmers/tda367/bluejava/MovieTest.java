package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by marcus on 2014-04-02.
 */
public class MovieTest extends TestCase {

//    private Movie movie;

	private String json = "{\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg\",\n" +
            "            \"id\": 550,\n" +
            "            \"original_title\": \"Fight Club\",\n" +
            "            \"release_date\": \"1999-10-15\",\n" +
            "            \"poster_path\": \"/2lECpi35Hnbpa4y46JX0aY3AWTy.jpg\",\n" +
            "            \"popularity\": 61151.745000000003,\n" +
            "            \"title\": \"Fight Club\",\n" +
            "            \"vote_average\": 9.0999999999999996,\n" +
            "            \"vote_count\": 174\n" +
            "        }";

//    @Before
//    public void initialize() {
//        try {
//            movie = new Movie(new JSONObject(json));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

	public void testToString() throws Exception {

    }

    @Test
    public void testGetID() throws Exception {
        String id = "550";
        Movie movie = new Movie(new JSONObject(json));
        assertEquals(id, movie.getID());
    }

    @Test
    public void testGetTitle() throws Exception {
        String title = "Fight Club";
        Movie movie = new Movie(new JSONObject(json));
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testGetReleaseYear() throws Exception {
        String releaseYear = "1999-10-15";
        Movie movie = new Movie(new JSONObject(json));
        assertEquals(releaseYear, movie.getReleaseYear());
    }

    @Test
    public void testGetPopularity() throws Exception {
        String popularity = "61151.745000000003";
        Movie movie = new Movie(new JSONObject(json));
        assertEquals(popularity, movie.getPopularity());
    }

    @Test
    public void testGetRating() throws Exception {
        String rating = "9.0999999999999996";
        Movie movie = new Movie(new JSONObject(json));
        assertEquals(rating, movie.getRating());
    }

    @Test
    public void testGetVoteCount() throws Exception {
        String voteCount = "174";
        Movie movie = new Movie(new JSONObject(json));
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
