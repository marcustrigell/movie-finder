package se.chalmers.tda367.bluejava.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.CastTabArrayAdapter;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

public class MovieCastTabFragment extends MovieTabFragment {

    private ListView listView;

    public static MovieCastTabFragment newInstance(Movie movie) {
        MovieCastTabFragment tab = new MovieCastTabFragment();

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
        if (movie.getCredits() == null) {
            Log.e("", "get cast");
            httpHandler.get(movieApi.getMovieCreditsQuery(id), this);
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
            Credits credits = new Credits(new JSONObject(json));
            movie = new Movie.Builder(movie).credits(credits).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        populateLayout();
    }

    /**
     * Populates the fragment's view with cast information
     */
    public void populateLayout() {
        Credits credits = movie.getCredits();
        List<Actor> cast = credits.getCast();
        listView.setAdapter(new CastTabArrayAdapter(context, (Activity) context, cast));
    }
}
