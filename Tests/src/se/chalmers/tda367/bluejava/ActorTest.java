package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;

public class ActorTest extends TestCase {

    private String json = "{\n" +
            "            \"cast_id\": 5,\n" +
            "            \"character\": \"Tyler Durden\",\n" +
            "            \"credit_id\": \"52fe4250c3a36847f80149f7\",\n" +
            "            \"id\": 287,\n" +
            "            \"name\": \"Brad Pitt\",\n" +
            "            \"order\": 1,\n" +
            "            \"profile_path\": \"/kc3M04QQAuZ9woUvH3Ju5T7ZqG5.jpg\",\n" +
            "        }";

    public void testGetCastID() throws Exception {
        String castID = "5";
        Actor test = new Actor(new JSONObject(json));
        assertEquals(castID, test.getCastID());
    }

    public void testGetCharacter() throws Exception {
        String character = "Brad Pitt";
        Actor test = new Actor(new JSONObject(json));
        assertEquals(character, test.getCharacter());
    }

    public void testGetCreditID() throws Exception {
        String creditID = "Brad Pitt";
        Actor test = new Actor(new JSONObject(json));
        assertEquals(creditID, test.getCreditID());
    }

    public void testGetOrder() throws Exception {
        String order = "Brad Pitt";
        Actor test = new Actor(new JSONObject(json));
        assertEquals(order, test.getOrder());
    }
}