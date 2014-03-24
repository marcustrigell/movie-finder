package se.chalmers.tda367.bluejava;

import android.app.Application;
import android.net.http.AndroidHttpClient;

public class BlueJava extends Application {
    private AndroidHttpClient androidHttpClient;

    public AndroidHttpClient getHttpClient() {
        if (androidHttpClient == null) {
            androidHttpClient = AndroidHttpClient.newInstance("Android");
        }
        return androidHttpClient;
    }

}
