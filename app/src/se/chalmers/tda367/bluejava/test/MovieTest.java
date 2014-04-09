package se.chalmers.tda367.bluejava.test;

import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marcus on 2014-04-02.
 */
public class MovieTest extends TestCase {

	private String id, title, releaseYear, popularity, rating, voteCount, posterPath;

	private String json = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"" +
			"/5vZw7ltCKI0JiOYTtRxaIC3DX0e.jpg\",\"id\":98,\"original_title\":\"Gladiator\"," +
			"\"release_date\":\"2000-05-01\",\"poster_path\":\"/6WBIzCgmDCYrqh64yDREGeDk9d3.jpg\"" +
			",\"popularity\":8.40221519445413,\"title\":\"Gladiator\",\"vote_average\":7.2,\"vote_count\"" +
			":1901}],\"total_pages\":1,\"total_results\": 1}";

	public void fromJsonToString(JSONObject jsonObject) {
		try {
			id = jsonObject.getString("id");
			title = jsonObject.getString("title");
			releaseYear = jsonObject.getString("release_date");
			popularity = jsonObject.getString("popularity");
			rating = jsonObject.getString("vote_average");
			voteCount = jsonObject.getString("vote_count");
			posterPath = jsonObject.getString("poster_path");
		}
		catch (JSONException e) {
			throw new RuntimeException("JSON parsing in Movie.java error!!");
		}
	}

	public void testToString() throws Exception {

    }

    public void testGetID() throws Exception {

    }

    public void testGetTitle() throws Exception {

    }

    public void testGetReleaseYear() throws Exception {

    }

    public void testGetPopularity() throws Exception {

    }

    public void testGetRating() throws Exception {

    }

    public void testGetVoteCount() throws Exception {

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
