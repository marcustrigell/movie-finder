package se.chalmers.tda367.bluejava.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.models.BlueJava;

public class DisplayProfileActivity extends FragmentActivity {

    private String fbAccessToken;

    private LoginFragment loginFragment;

    private boolean isLoggedIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        if (savedInstanceState == null) {
            loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, loginFragment)
                    .commit();
        } else {
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }

    public void hasLoggedIn(String FBaccessToken) {
        this.fbAccessToken = FBaccessToken;

        if (!isLoggedIn) {
            invalidateOptionsMenu();
            BlueJava blueJavaApplication = (BlueJava) getApplication();
            blueJavaApplication.setUserFBAuthToken(FBaccessToken);
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
