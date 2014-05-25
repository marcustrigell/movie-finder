package se.chalmers.tda367.bluejava.models;

import android.app.Application;
import android.content.Context;
import android.net.http.AndroidHttpClient;

/**
 * This class represents the whole application
 */
public class BlueJava extends Application {

    /**
     * Intent keys used to send (Parcelable) extras to Activities and Fragments
     */
    public static String EXTRA_MOVIE = "se.chalmers.tda367.bluejava.extra.MOVIE";

    public static String EXTRA_PERSON = "se.chalmers.tda367.bluejava.extra.PERSON";

    public final static String EXTRA_MESSAGE = "se.chalmers.tda367.bluejava.extra.MESSAGE";

    public static String EXTRA_MOVIE_FAVORITES = "se.chalmers.tda367.bluejava.extra.MOVIE_FAVORITES";

    public static String EXTRA_FB_USER_INFO = "se.chalmers.tda367.bluejava.extra.FB_USER_INFO";

    public static String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    private static Context context;

    private String userFBAuthToken;

    private AndroidHttpClient androidHttpClient;

    public void onCreate() {
        super.onCreate();
        BlueJava.context = getApplicationContext();
    }

    public String getUserFBAuthToken() {
        return userFBAuthToken;
    }

    public void setUserFBAuthToken(String userFBAuthToken) {
        this.userFBAuthToken = userFBAuthToken;
    }

    public static Context getContext() {
        return BlueJava.context;
    }

    public AndroidHttpClient getHttpClient() {
        if (androidHttpClient == null) {
            androidHttpClient = AndroidHttpClient.newInstance("Android");
        }
        return androidHttpClient;
    }



}
