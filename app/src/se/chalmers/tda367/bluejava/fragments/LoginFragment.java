package se.chalmers.tda367.bluejava.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.ImageAdapter;
import se.chalmers.tda367.bluejava.interfaces.FBAuthenticator;
import se.chalmers.tda367.bluejava.interfaces.MovieFavoritesDB;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.sqlite.MovieFavoritesDbHelper;

import java.util.Arrays;
import java.util.List;

public class LoginFragment extends Fragment {

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private UiLifecycleHelper uiHelper;

    private FBAuthenticator fbAuthenticator;

    private LoginButton authButton;

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

        return view;
    }

    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        final TextView userInfoTextView = (TextView) getView().findViewById(R.id.userInfoTextView);

        if (state.isOpened()) {

            authButton.setVisibility(View.GONE);

            if (fbAuthenticator == null) {

                fbAuthenticator = (FBAuthenticator) getActivity();

                userInfoTextView.setVisibility(View.VISIBLE);

                // Request user data and show the results
                Request.newMeRequest(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {
                                userInfoTextView.setText(buildUserInfoDisplay(user));
                        }
                    }
                }).executeAsync();

            } else {
                fbAuthenticator.hasLoggedIn(session.getAccessToken());
            }
        } else if (state.isClosed()) {
            userInfoTextView.setVisibility(View.GONE);
            tellFBAuthenticatorLogout();
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

        List<Movie> movies = movieFavoritesDb.getAllMovies();

        if (movies.size() > 0) {
            GridView favoritesGridView = (GridView) getView().findViewById(R.id.profile_grid_view);
            favoritesGridView.setAdapter(new ImageAdapter(getView().getContext(), getActivity(), movies));
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

    private String buildUserInfoDisplay(GraphUser user) {
        StringBuilder userInfo = new StringBuilder("");

        userInfo.append(String.format("Name: %s\n\n",
                user.getName()));

        userInfo.append(String.format("Birthday: %s\n\n",
                user.getBirthday()));

        userInfo.append(String.format("Location: %s\n\n",
                user.getLocation().getProperty("name")));

        userInfo.append(String.format("Locale: %s\n\n",
                user.getProperty("locale")));

        return userInfo.toString();
    }
}
