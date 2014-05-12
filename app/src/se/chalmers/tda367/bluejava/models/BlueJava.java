package se.chalmers.tda367.bluejava.models;

import android.app.Application;
import android.content.Context;
import android.net.http.AndroidHttpClient;

public class BlueJava extends Application {

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
