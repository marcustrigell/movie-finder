package se.chalmers.tda367.bluejava.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.*;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.ShareFavoritesArrayAdapter;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.interfaces.FbMovieSharer;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.ArrayList;

/**
 * This class is responsible for sharing posts to Facebook.
 * When the user picks a movie, a message will be created with
 * the movie's cover photo, title and description.
 * The user can also write a few words if the want to before posting.
 *
 * Before doing this, the user must be authenticated with Facebook.
 */
public class ShareMoviesActivity extends FragmentActivity implements FbMovieSharer {

    private UiLifecycleHelper uiHelper;

    private String userInfo;

    private ArrayList<Movie> movies;

    private TextView facebookShareText;

    /**
     * Build the view components, attach the objects received
     * and create the Facebook UI Helper and associate it with
     * a callback method to handle the user's Facebook session.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_share_movies);

        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        Intent intent = getIntent();

        userInfo = intent.getStringExtra(BlueJava.EXTRA_FB_USER_INFO);

        movies = intent.getParcelableArrayListExtra(BlueJava.EXTRA_MOVIE_FAVORITES);

        createView();
    }

    /**
     * Provides asynchronous notification of Session state changes.
     */
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    /**
     * Create and make the view components ready for later use.
     */
    private void createView() {
        facebookShareText = (TextView) findViewById(R.id.share_movies_welcome_message);
    }

    /**
     * When the user has attempted to post a movie to
     * their wall, this method will handle the callback.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.e("Activity", "Success!");
            }
        });
    }

    /**
     * We can now populate the view, so fill it with
     * favorite movies ready for the user to choose
     * and post.
     */
    @Override
    public void onResume() {
        super.onResume();

        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }

        facebookShareText.setText(userInfo);

        showFavorites();

        uiHelper.onResume();
    }

    /**
     * When something happens with a Facebook Session
     * it should be handled accordingly here.
     *
     * If they're not logged in, they should not be shown the
     * ability to post to Facebook.
     */
    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        if (state.isClosed()) {
            Intent intent = new Intent(this, DisplayProfileActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    /**
     * If the user has the native Facebook app installed on
     * their phone, post the movie using it.
     *
     * Otherwise fallback to the Feed Dialog (a web dialog)
     */
    @Override
    public void shareMovieToFB(Movie movie) {
        final MovieApi movieApi = new MovieApi();

        String title = movie.getTitle();
        String description = "Released: " + movie.getReleaseYear();
        String coverUrl = movieApi.getThumbnailURL(movie.getPosterPath());
        String movieUrl = movieApi.getPublicUrl(movie.getID());

        if (FacebookDialog.canPresentShareDialog(BlueJava.getContext(),
                FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {

            FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
                    .setName(title)
                    .setDescription(description)
                    .setPicture(coverUrl)
                    .setLink(movieUrl)
                    .build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else {
            Bundle params = new Bundle();
            params.putString("name", title);
            params.putString("description", description);
            params.putString("link", movieUrl);
            params.putString("picture", coverUrl);

            WebDialog feedDialog = (
                    new WebDialog.FeedDialogBuilder(this,
                            Session.getActiveSession(),
                            params))
                    .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                        @Override
                        public void onComplete(Bundle values,
                                               FacebookException error) {
                            if (error == null) {
                                // When the story is posted, echo the success
                                // and the post Id.
                                final String postId = values.getString("post_id");
                                if (postId != null) {
                                    Toast.makeText(getApplicationContext(),
                                            "Posted story, id: " + postId,
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // User clicked the Cancel button
                                    Toast.makeText(getApplicationContext(),
                                            "Publish cancelled",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else if (error instanceof FacebookOperationCanceledException) {
                                // User clicked the "x" button
                                Toast.makeText(getApplicationContext(),
                                        "Publish cancelled",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Generic, ex: network error
                                Toast.makeText(getApplicationContext(),
                                        "Error posting story",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .build();
            feedDialog.show();
        }
    }

    public void showFavorites() {
        if (movies.size() > 0) {
            ListView favoritesListView = (ListView) findViewById(R.id.share_movies_list);
            favoritesListView.setAdapter(new ShareFavoritesArrayAdapter(this, this, movies));
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		// Associate the searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}
}
