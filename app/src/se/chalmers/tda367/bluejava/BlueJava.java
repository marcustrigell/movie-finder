package se.chalmers.tda367.bluejava;

import android.app.Application;
import android.content.Context;
import android.net.http.AndroidHttpClient;

public class BlueJava extends Application {

    private static Context context;

    private AndroidHttpClient androidHttpClient;

    public void onCreate() {
        super.onCreate();
        BlueJava.context = getApplicationContext();
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
