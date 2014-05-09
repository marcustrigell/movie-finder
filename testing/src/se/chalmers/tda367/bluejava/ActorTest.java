package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.*;
import se.chalmers.tda367.bluejava.models.Actor;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class ActorTest extends TestCase {

    private static Actor actor;
    private final int CAST_ID = 4;
    private final String CHARACTER = "The Narrator";
    private final String CREDIT_ID = "52fe4250c3a36847f80149f3";
    private final int ORDER = 0;

    @Override
    public void setUp() throws Exception{
        super.setUp();
        String json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/actor.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        actor = new Actor(new JSONObject(json));
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
