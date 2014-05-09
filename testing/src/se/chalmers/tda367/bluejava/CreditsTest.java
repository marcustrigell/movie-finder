package se.chalmers.tda367.bluejava;

import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class CreditsTest extends TestCase {

    private Credits credits;
    private final int CREDITS_ID = 550;
    private List<Actor> cast;
    private List<CrewMember> crew;
    private String json;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/credits.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        credits = new Credits(new JSONObject(json));
    }

    @Test
    public void testGetCreditsID() {
        assertEquals(CREDITS_ID, credits.getID());
    }

    @Test
    public void testGetCast() throws JSONException {
        cast = Actor.jsonToListOfActors(new JSONObject(json));
        for(int i = 0; i < cast.size(); i++) {
            assertEquals(cast.get(i).getID(), credits.getCast().get(i).getID());
        }
    }

    @Test
    public void testGetCrew() throws JSONException {
        crew = CrewMember.jsonToListOfCrewMembers(new JSONObject(json));
        for(int i = 0; i < crew.size(); i++) {
            assertEquals(crew.get(i).getID(), credits.getCrew().get(i).getID());
        }
    }

}
