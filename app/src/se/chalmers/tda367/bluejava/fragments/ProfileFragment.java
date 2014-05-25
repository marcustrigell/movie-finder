package se.chalmers.tda367.bluejava.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.ShareMoviesActivity;
import se.chalmers.tda367.bluejava.adapters.MovieCoversAdapter;
import se.chalmers.tda367.bluejava.interfaces.FBAuthenticator;
import se.chalmers.tda367.bluejava.interfaces.MovieFavoritesDB;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.sqlite.MovieFavoritesDbHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileFragment extends Fragment {

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private UiLifecycleHelper uiHelper;

    private FBAuthenticator fbAuthenticator;

    private LoginButton authButton;

    private TextView welcomeText;

    private ArrayList<Movie> movies;

    private String userInfo;

    private static final int SHARE_BUTTON_ID = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        authButton = (LoginButton) view.findViewById(R.id.authButton);

        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes"));

        welcomeText = (TextView) view.findViewById(R.id.profile_welcome_message);

        return view;
    }

    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {

        if (state.isOpened()) {

            createLoggedInView();
            //authButton.setVisibility(View.GONE);

            welcomeText.setText(R.string.profile_logged_in_message);

            if (fbAuthenticator == null) {

                fbAuthenticator = (FBAuthenticator) getActivity();

                // Request user data and save the result
                Request.newMeRequest(session, new Request.GraphUserCallback() {

                    // callback after Graph API response with user object
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {
                            userInfo = buildUserInfoString(user);
                        }
                    }
                }).executeAsync();

            } else {
                fbAuthenticator.hasLoggedIn(session.getAccessToken());
            }
        } else if (state.isClosed()) {
            tellFBAuthenticatorLogout();
            removeLoggedInView();
        }
    }

    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return session != null && session.isOpened();
    }

    @Override
    public void onResume() {
        super.onResume();

        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }

        showFavorites();

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
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

    public void showFavorites() {
        final MovieFavoritesDB movieFavoritesDb = new MovieFavoritesDbHelper(getView().getContext());

        movies = (ArrayList<Movie>) movieFavoritesDb.getAllMovies();

        if (movies.size() > 0) {
            GridView favoritesGridView = (GridView) getView().findViewById(R.id.profile_grid_view);
            favoritesGridView.setAdapter(new MovieCoversAdapter(getView().getContext(), getActivity(), movies));
        }
    }

    public void logout() {
        Session session = Session.getActiveSession();
        if (session != null) {
            session.closeAndClearTokenInformation();
            tellFBAuthenticatorLogout();
        }
    }

    private void tellFBAuthenticatorLogout() {
        if (fbAuthenticator != null) {
            fbAuthenticator.hasLoggedOut();
        }
    }

    /**
     * Creates a button that will take the user
     * to a new Activity where they can choose a
     * movie to share on Facebook.
     *
     * It will send details (fetched from Facebook)
     * about the user aswell as a list of movies.
     */
    public void createLoggedInView() {

        if (!isLoggedIn()) {
            return;
        }

        RelativeLayout.LayoutParams buttonTextParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        buttonTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        buttonTextParams.leftMargin = 380;
        buttonTextParams.topMargin = 42;

        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        buttonParams.leftMargin = 440;

        TextView buttonText = new TextView(getActivity());
        buttonText.setTextColor(getResources().getColor(R.color.grey_dark));
        buttonText.setText("or");

        buttonText.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        Button shareButton = new Button(getActivity());
        shareButton.setId(SHARE_BUTTON_ID);

        shareButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShareMoviesActivity.class);

                intent.putExtra(BlueJava.EXTRA_FB_USER_INFO, userInfo);

                intent.putParcelableArrayListExtra(BlueJava.EXTRA_MOVIE_FAVORITES, movies);

                getActivity().startActivity(intent);
            }
        });

        shareButton.setText("Share a movie");
        shareButton.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        RelativeLayout facebookButtonRow = (RelativeLayout) getView().findViewById(R.id.facebook_buttons_row);

        facebookButtonRow.addView(buttonText, buttonTextParams);
        facebookButtonRow.addView(shareButton, buttonParams);
    }

    private void removeLoggedInView() {
        Button shareButton = (Button) getActivity().findViewById(SHARE_BUTTON_ID);
        shareButton.setVisibility(View.GONE);
    }

    private String buildUserInfoString(GraphUser user) {
        StringBuilder userInfo = new StringBuilder("Hi, ");

        userInfo.append(user.getName() + "! ");

        userInfo.append("Hope you're having a good time in " +
                user.getLocation().getProperty("name") + ". " +
                "Choose a movie below to share it to your friends!"
        );

        return userInfo.toString();
    }
}
