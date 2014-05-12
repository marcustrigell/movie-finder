package se.chalmers.tda367.bluejava.notworking;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.models.Credits;
import se.chalmers.tda367.bluejava.tests.R;

//TODO fix the error associated with this class and credits.xml

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class CreditsTest extends InstrumentationTestCase {

    private Credits credits, creditsFromParcel;
    private final int CREDITS_ID = 550;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.credits);
        credits = new Credits(new JSONObject(json));
        Parcel parcel = Parcel.obtain();
        credits.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        creditsFromParcel = Credits.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testGetCreditsID() {
        assertEquals(CREDITS_ID, credits.getID());
    }
}
