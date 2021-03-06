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
public class CrewMemberTest extends InstrumentationTestCase {

    private CrewMember crewMember, crewMemberFromParcel;
    private final String creditId = "52fe4250c3a36847f80149ef";
    private final String department = "Writing";
    private final String job = "Author";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.crew_member);
        crewMember = new CrewMember(new JSONObject(json));
        Parcel parcel = Parcel.obtain();
        crewMember.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        crewMemberFromParcel = CrewMember.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testParcelable() {
        assertEquals(crewMember.describeContents(), crewMemberFromParcel.describeContents());
    }

    @Test
    public void testGetCreditID() {
        assertEquals(creditId, crewMember.getCreditID());
    }

    @Test
    public void testGetDepartment() {
        assertEquals(department, crewMember.getDepartment());
    }

    @Test
    public void testGetJob() {
        assertEquals(job, crewMember.getJOB());
    }
}
