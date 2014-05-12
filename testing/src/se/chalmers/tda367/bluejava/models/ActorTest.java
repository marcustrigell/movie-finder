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
public class ActorTest extends InstrumentationTestCase {

    private Actor actor, actorFromParcel;
    private final int CAST_ID = 4;
    private final String CHARACTER = "The Narrator";
    private final String CREDIT_ID = "52fe4250c3a36847f80149f3";
    private final int ORDER = 0;

    @Override
    public void setUp() throws Exception{
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.actors);
        actor = new Actor(new JSONObject(json));
        Parcel parcel = Parcel.obtain();
        actor.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        actorFromParcel = Actor.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testParcelable() {
        assertEquals(actor.describeContents(), actorFromParcel.describeContents());
    }

    @Test
    public void testGetCastID() throws Exception {
        assertEquals(CAST_ID, actor.getCastID());
    }

    @Test
    public void testGetCharacter() throws Exception {
        assertEquals(CHARACTER, actor.getCharacter());
    }

    @Test
    public void testGetCreditID() throws Exception {
        assertEquals(CREDIT_ID, actor.getCreditID());
    }

    @Test
    public void testGetOrder() throws Exception {
        assertEquals(ORDER, actor.getOrder());
    }
}
