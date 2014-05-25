package se.chalmers.tda367.bluejava.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.fragments.ProfileFragment;
import se.chalmers.tda367.bluejava.interfaces.FBAuthenticator;
import se.chalmers.tda367.bluejava.models.BlueJava;

/**
 * This class represents the user's personal profile
 * It shows the movies they've marked as favorites.
 *
 * If they login to Facebook, which is done here as well,
 * they have the ability to share movies to their Facebook wall.
 */
public class DisplayProfileActivity extends FragmentActivity implements FBAuthenticator {

    private String fbAccessToken;

    private ProfileFragment profileFragment;

    private boolean isLoggedIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            profileFragment = new ProfileFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, profileFragment)
                    .commit();
        } else {
            profileFragment = (ProfileFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }

    public void hasLoggedIn(String FBaccessToken) {
        fbAccessToken = FBaccessToken;

        if (!isLoggedIn) {
            invalidateOptionsMenu();
            BlueJava app = (BlueJava) getApplication();
            app.setUserFBAuthToken(FBaccessToken);
            Toast.makeText(this, getString(R.string.fb_logged_in), Toast.LENGTH_SHORT).show();
            isLoggedIn = true;
        }
    }

    public void hasLoggedOut() {
        invalidateOptionsMenu();
        fbAccessToken = null;

        BlueJava app = (BlueJava) getApplication();
        app.setUserFBAuthToken(null);

        Toast.makeText(this, getString(R.string.fb_logged_out), Toast.LENGTH_SHORT).show();
        isLoggedIn = false;
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
