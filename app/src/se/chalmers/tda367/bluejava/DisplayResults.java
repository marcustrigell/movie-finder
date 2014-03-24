package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Marcus on 2014-03-24.
 * @author Marcus Trigell
 */
public class DisplayResults extends Activity{

    public void onCreate(Intent intent, String searchInput) {   //TODO should intent be here?
        super.onCreate();
        try {
            getResults(new AndroidHttpClient(), searchInput);
        } catch () {    //TODO suitable exception here (if no results or likewise)
            ;
        }
    }

    private String toMovieDBString(String searchInput) {
        String movieDB = "/3/search/movie/";
        return movieDB + searchInput;
    }

    private ArrayList<JSONObject> getResults(AndroidHttpClient httpClient, String searchInput) {
        //HTTPHandler httpHandler = new HTTPHandler(httpClient);
        try {
            //return httpHandler.getRequest(toMovieDBString(searchInput));  //TODO
        } catch () {    //TODO suiatable exception here (if bad request or likewise)

        }
    }
}
