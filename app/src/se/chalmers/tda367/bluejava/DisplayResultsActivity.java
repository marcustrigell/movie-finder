package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;


public class DisplayResultsActivity extends Activity {

    private static final String TAG = "GetMoviesActivity";

    private AndroidHttpClient httpClient;
    private HttpHandler httpHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String query = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        httpClient = HttpHandler.getAndroidHttpClient(this);
        httpHandler = new HttpHandler(httpClient);

    }

    public void handleSearchResults(String result) {

    }
}
