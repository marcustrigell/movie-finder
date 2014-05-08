package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONObject;

public class PersonTest extends TestCase {

    private String json = "{\n" +
                "            \"cast_id\": 5,\n" +
                "            \"character\": \"Tyler Durden\",\n" +
                "            \"credit_id\": \"52fe4250c3a36847f80149f7\",\n" +
                "            \"id\": 287,\n" +
                "            \"name\": \"Brad Pitt\",\n" +
                "            \"order\": 1,\n" +
                "            \"profile_path\": \"/kc3M04QQAuZ9woUvH3Ju5T7ZqG5.jpg\",\n" +
                "        }";

    public void testGetName() throws Exception {
        String name = "Brad Pitt";
        Person test = new Actor(new JSONObject(json));
        assertEquals(name, test.getName());
    }

    public void testGetId() throws Exception {
        String id = "287";
        Person test = new Actor(new JSONObject(json));
        assertEquals(id, test.getId());
    }

    public void testGetProfilePath() throws Exception {
        String profilePath = "/kc3M04QQAuZ9woUvH3Ju5T7ZqG5.jpg";
        Person test = new Actor(new JSONObject(json));
        assertEquals(profilePath, test.getProfilePath());
    }
}