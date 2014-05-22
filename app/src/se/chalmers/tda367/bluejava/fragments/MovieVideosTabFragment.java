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
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.Video;

import java.util.List;

public class MovieVideosTabFragment extends MovieTabFragment {

    private ListView listView;

    public static MovieVideosTabFragment newInstance(Movie movie) {
        MovieVideosTabFragment tab = new MovieVideosTabFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);

        tab.setArguments(bundle);

        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_movie_cast, container, false);
        listView = (ListView) view.findViewById(R.id.castList);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAdditionalInfo(movie.getID());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {

            if (!isVisibleToUser) {
                //Log.e("MyFragment", "Cast NOT visible.");

            }
        }
    }

    protected void getAdditionalInfo(int id) {
        if (movie.getVideos() == null || movie.getVideos().size() == 0) {
            httpHandler.get(movieApi.getMovieVideosQuery(id), this);
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
