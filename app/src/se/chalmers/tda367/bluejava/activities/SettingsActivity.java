package se.chalmers.tda367.bluejava.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import se.chalmers.tda367.bluejava.R;

/**
 * Created by axelniklasson on 2014-04-28.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loading the settings from the XML file
        addPreferencesFromResource(R.xml.settings);
    }
}