package se.chalmers.tda367.bluejava.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.fragments.ProfileFragment;
import se.chalmers.tda367.bluejava.interfaces.FBAuthenticator;
import se.chalmers.tda367.bluejava.models.BlueJava;

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
        this.fbAccessToken = FBaccessToken;

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

}
