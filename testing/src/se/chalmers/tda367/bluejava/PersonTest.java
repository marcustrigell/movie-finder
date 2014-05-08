package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.junit.BeforeClass;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class PersonTest extends TestCase {

    private static Person person;
    private final String NAME = "Markus Redmond";
    private final String ID = "1226835";
    private final String PROFILE_PATH = "/yxMbPCGa8rMSrquc8v4UN7QLlWX.jpg";

    @BeforeClass
    public static void initialize() throws Exception {
        String json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/person.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        //Since Person is abstract, subclass is used for testing
        person = new Actor(new org.json.JSONObject(json));
    }

    public void testGetName() {
        assertEquals(NAME, person.getName());
    }

    public void testGetID() {
        assertEquals(ID, person.getId());
    }

    public void testGetProfilePath() {
        assertEquals(PROFILE_PATH, person.getProfilePath());
    }
}
