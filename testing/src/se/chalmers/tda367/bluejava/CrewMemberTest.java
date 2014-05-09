package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.models.CrewMember;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class CrewMemberTest extends TestCase {

    private static CrewMember crewMember;
    private final String CREDIT_ID = "52fe4250c3a36847f80149ef";
    private final String DEPARTMENT = "Writing";
    private final String JOB = "Author";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        String json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/crew_member.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        crewMember = new CrewMember(new JSONObject(json));
    }

    @Test
    public void testGetCreditID() {
        assertEquals(CREDIT_ID, crewMember.getCreditID());
    }

    @Test
    public void testGetDepartment() {
        assertEquals(DEPARTMENT, crewMember.getDepartment());
    }

    @Test
    public void testGetJob() {
        assertEquals(JOB, crewMember.getJOB());
    }
}
