package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Class for managing the credits in a movie.
 */
public class Credits {

    private final String creditsID;

    private final List<Actor> cast;

    private final List<CrewMember> crew;

    public Credits(JSONObject jsonObject) throws JSONException {
        creditsID = jsonObject.getString("id");
        cast = Actor.jsonToListOfActors(jsonObject);
        crew = CrewMember.jsonToListOfCrewMembers(jsonObject);
    }

    public String getCreditsID() {
        return creditsID;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }
}
