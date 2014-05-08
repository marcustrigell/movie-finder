package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.junit.*;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class ActorTest extends TestCase {

    private static Actor actor;
    private final String CAST_ID = "4";
    private final String CHARACTER = "The Narrator";
    private final String CREDIT_ID = "52fe4250c3a36847f80149f3";
    private final String ORDER = "0";

    @BeforeClass
    public static void initialize() throws Exception {
        String json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/actor.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        actor = new Actor(new org.json.JSONObject(json));
    }

    @Test
    public void testGetCastID() {
        assertEquals(CAST_ID, actor.getCastID());
    }

    @Test
    public void testGetCharacter() {
        assertEquals(CHARACTER, actor.getCharacter());
    }

    @Test
    public void testGetCreditID() {
        assertEquals(CREDIT_ID, actor.getCreditID());
    }

    @Test
    public void testGetOrder() {
        assertEquals(ORDER, actor.getOrder());
    }

}
