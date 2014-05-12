package se.chalmers.tda367.bluejava.models;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.tests.R;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class PersonTest extends InstrumentationTestCase {

    private Person person, personFromParcel;
    private final String NAME = "Markus Redmond";
    private final int ID = 1226835;
    private final String PROFILE_PATH = "/yxMbPCGa8rMSrquc8v4UN7QLlWX.jpg";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.person);
        person = new Actor(new JSONObject(json));
        Parcel parcel = Parcel.obtain();
        person.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        personFromParcel = Actor.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testParcelable() {
        assertEquals(person.describeContents(), personFromParcel.describeContents());
    }

    public void testGetName() {
        assertEquals(NAME, person.getName());
    }

    public void testGetID() {
        assertEquals(ID, person.getID());
    }

    public void testGetProfilePath() {
        assertEquals(PROFILE_PATH, person.getProfilePath());
    }


}
