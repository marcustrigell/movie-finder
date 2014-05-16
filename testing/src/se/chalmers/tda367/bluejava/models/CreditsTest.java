package se.chalmers.tda367.bluejava.models;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.tests.R;

import java.util.List;

//TODO fix the error associated with this class and credits.xml

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class CreditsTest extends InstrumentationTestCase {

    private Credits credits, creditsFromParcel;
    private final int creditsId = 550;
    private List<Actor> cast;
    private List<CrewMember> crew;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String creditsJSON = context.getString(R.string.credits);
        credits = new Credits(new JSONObject(creditsJSON));

        Parcel parcel = Parcel.obtain();
        credits.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        creditsFromParcel = Credits.CREATOR.createFromParcel(parcel);

        //Initializing lists
        cast = Actor.jsonToListOfActors(new JSONObject(creditsJSON));
        crew = CrewMember.jsonToListOfCrewMembers(new JSONObject(creditsJSON));
    }

    @Test
    public void testParcelable() {
        assertEquals(credits.describeContents(), creditsFromParcel.describeContents());
    }

    @Test
    public void testGetCreditsID() {
        assertEquals(creditsId, credits.getID());
    }

    @Test
    public void testGetCast() {
        for(int i = 0; i < cast.size(); i++) {
            assertEquals(cast.get(i), credits.getCast().get(i));
        }
    }

    @Test
    public void testGetCrew() {
        for(int i = 0; i < crew.size(); i++) {
            assertEquals(crew.get(i), credits.getCrew().get(i));
        }
    }
}
