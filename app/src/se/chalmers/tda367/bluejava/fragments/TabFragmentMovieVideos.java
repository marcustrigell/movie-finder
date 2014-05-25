package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.VideosTabArrayAdapter;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.Video;

import java.util.List;

/**
 * Displays youtube videos related to a movie in a list view
 * in one of the DisplayMovieActivity's tabs.
 *
 * Choosing a video will trigger the phone to start a
 * new application that can play the video. By default this is
 * the native youtube app.
 */
public class TabFragmentMovieVideos extends TabFragment {

    private Movie movie;

    private ListView listView;

    public static TabFragmentMovieVideos newInstance(Movie movie) {
        TabFragmentMovieVideos tab = new TabFragmentMovieVideos();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BlueJava.EXTRA_MOVIE, movie);

        tab.setArguments(bundle);

        return tab;
    }

    @Override
    public void init() {
        super.init();
        movie = getArguments().getParcelable(BlueJava.EXTRA_MOVIE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_cast, container, false);
        listView = (ListView) view.findViewById(R.id.castList);
        return view;
    }

    public void sendHttpGetRequest() {

        if (movie.getVideos() == null || movie.getVideos().size() == 0) {
            httpHandler.get(movieApi.getMovieVideosQuery(movie.getID()), this);
        } else {
            populateLayout();
        }
    }

    @Override
    public void handleJSONResult(String json) {

        if (json == null) {
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            movie = new Movie.Builder(movie).videos(jsonObject).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        populateLayout();
    }

    public void populateLayout() {
        List<Video> videos = movie.getVideos();
        listView.setAdapter(new VideosTabArrayAdapter(context, (Activity) context, videos));
    }
}
