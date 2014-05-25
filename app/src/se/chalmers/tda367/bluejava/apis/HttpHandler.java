package se.chalmers.tda367.bluejava.apis;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import se.chalmers.tda367.bluejava.interfaces.JSONResultHandler;
import se.chalmers.tda367.bluejava.models.BlueJava;

import java.io.IOException;

/**
 * This class is responsible for all the applications HTTP
 * calls (except calls to Facebook). It makes GET requests in the
 * background using Android AsyncTasks.
 *
 * All classes using the HttpHandler should implement the
 * JSONResultHandler interface and pass itsef to the handler.
 *
 * All results returned by the server is then passed to the JSONResultHandlers
 * handleJSONResult() method.
 */
public class HttpHandler {
    private final AndroidHttpClient httpClient;

    public HttpHandler(AndroidHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void get(String url, JSONResultHandler jsonResultHandler) {
        AsyncTask getMoviesAsyncTask = new GetMoviesAsyncTask(url, jsonResultHandler).
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