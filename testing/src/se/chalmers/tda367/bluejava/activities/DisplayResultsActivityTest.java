package se.chalmers.tda367.bluejava.activities;

import android.content.Intent;
import android.test.AndroidTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by axelniklasson on 2014-05-13.
 */
public class DisplayResultsActivityTest extends AndroidTestCase {

    private DisplayResultsActivity activity;
    private Intent [] intents = new Intent[5];
    private String[] searchQueries = {"gladiator", "gladiator ", " gladiator", " gladiator ", "gladi ator"};

    @Before
    public void setUp() throws Exception {
        activity = new DisplayResultsActivity();
        for(int i = 0; i < searchQueries.length; i++) {
            intents[i] = new Intent(searchQueries[i]);
        }
    }

    @Test
    public void testHandleIntent() throws Exception {
        String[] searchQueries = {"gladiator", "gladiator ", " gladiator", " gladiator ", "gladi ator"};
        //Vad ska göras egentligen?
    }
}
