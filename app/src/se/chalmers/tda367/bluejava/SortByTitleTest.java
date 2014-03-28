package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by marcus on 2014-03-27.
 */
public class SortByTitleTest extends TestCase {

    public void testSort() throws Exception {
        String json = "{\n" +
                "    \"page\": 1,\n" +
                "    \"results\": [\n" +
                "        {\n" +
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
                "        },\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/5Z0FScA1bB6EbdGmZCUBeUk32eV.jpg\",\n" +
                "            \"id\": 14476,\n" +
                "            \"original_title\": \"Clubbed\",\n" +
                "            \"release_date\": \"2008-10-02\",\n" +
                "            \"poster_path\": \"/bl6PEQtmohEP1zP9srNZY6bXyHg.jpg\",\n" +
                "            \"popularity\": 1.7290000000000001,\n" +
                "            \"title\": \"Clubbed\",\n" +
                "            \"vote_average\": 7.7999999999999998,\n" +
                "            \"vote_count\": 3\n" +
                "        },\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/qw2Qb42xtyE1B449JoTgb1mVCe1.jpg\",\n" +
                "            \"id\": 51021,\n" +
                "            \"original_title\": \"Lure: Teen Fight Club\",\n" +
                "            \"release_date\": \"2010-11-16\",\n" +
                "            \"poster_path\": \"/aRTX5Y52yGbVL6TGnyI4E8jjtz4.jpg\",\n" +
                "            \"popularity\": 0.26600000000000001,\n" +
                "            \"title\": \"Lure: Teen Fight Club\",\n" +
                "            \"vote_average\": 0.0,\n" +
                "            \"vote_count\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/tcoAGvTo96R7Y9ZGVCCz7BZvrvb.jpg\",\n" +
                "            \"id\": 104782,\n" +
                "            \"original_title\": \"Florence Fight Club\",\n" +
                "            \"release_date\": \"2010-01-01\",\n" +
                "            \"poster_path\": \"/eQqqu0srTYcclWqylvgpLyU87hV.jpg\",\n" +
                "            \"popularity\": 0.085000000000000006,\n" +
                "            \"title\": \"Florence Fight Club\",\n" +
                "            \"vote_average\": 0.0,\n" +
                "            \"vote_count\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": null,\n" +
                "            \"id\": 115584,\n" +
                "            \"original_title\": \"Fight Club – The “I am Jack’s Laryngitis” Edit\",\n" +
                "            \"release_date\": null,\n" +
                "            \"poster_path\": null,\n" +
                "            \"popularity\": 0.059999999999999998,\n" +
                "            \"title\": \"Fight Club – The “I am Jack’s Laryngitis” Edit\",\n" +
                "            \"vote_average\": 0.0,\n" +
                "            \"vote_count\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"total_pages\": 1,\n" +
                "    \"total_results\": 5\n" +
                "}";
        List<Movie> list = Movie.jsonToListOfMovies(json);
        for(Movie movie : list) {
            ; //stuff here
        }
        ISort sortMethod = new SortByTitle();
        sortMethod.sort(list);
        for(Movie movie : list) {
            ; //stuff here too
        }

    }
}
