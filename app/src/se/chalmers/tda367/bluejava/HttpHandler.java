package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpHandler {
    private static final String TAG = "HttpHandler";
    private final AndroidHttpClient httpClient;
    private AsyncTask getMoviesAsyncTask;

    public HttpHandler(AndroidHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void get(String url, JSONResultHandler jsonResultHandler) {
        getMoviesAsyncTask = new GetMoviesAsyncTask(url, jsonResultHandler).
                executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public String getData(String url) {
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                response.getEntity().consumeContent();
                return result;
            } else {
                return null;
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        return null;
    }

    private class GetMoviesAsyncTask extends AsyncTask<Void, Integer, String> {
        private final String url;
        private final JSONResultHandler jsonResultHandler;

        public GetMoviesAsyncTask(String url, JSONResultHandler jsonResultHandler) {
            this.url = url;
            this.jsonResultHandler = jsonResultHandler;
        }

        @Override
        protected String doInBackground(Void... v) {
            return getData(url);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v(TAG, result);
            if (jsonResultHandler != null) {
                jsonResultHandler.handleJSONResult(result);
            }
        }
    }

    public static AndroidHttpClient getAndroidHttpClient(Activity activity) {
        BlueJava app = (BlueJava) activity.getApplication();
        return app.getHttpClient();
    }
}