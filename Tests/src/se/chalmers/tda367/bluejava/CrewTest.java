package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;

public class CrewTest extends TestCase {

    private String json = "{\n" +
            "            \"credit_id\": \"52fe4250c3a36847f80149ef\",\n" +
            "            \"department\": \"Writing\",\n" +
            "            \"id\": 7469,\n" +
            "            \"job\": \"Author\",\n" +
            "            \"name\": \"Jim Uhls\",\n" +
            "            \"profile_path\": \"\",\n" +
            "        }";

    public void testGetCreditID() throws Exception {
        String creditID = "5";
        Crew test = new Crew(new JSONObject(json));
        assertEquals(creditID, test.getCreditID());
    }

    public void testGetDepartment() throws Exception {
        String department = "Writing";
        Crew test = new Crew(new JSONObject(json));
        assertEquals(department, test.getDepartment());
    }

    public void testGetJOB() throws Exception {
        String job = "Author";
        Crew test = new Crew(new JSONObject(json));
        assertEquals(job, test.getJOB());
    }
}