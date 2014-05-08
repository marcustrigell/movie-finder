package se.chalmers.tda367.bluejava;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class Main {

    public static void main(String[] main) throws FileNotFoundException, JSONException {
        String json = "";
        Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/credits.txt"));
        while(sc.hasNextLine()) {
            json += sc.nextLine();
        }
        Credits credits = new Credits(new JSONObject(json));
        System.out.println(credits.getCreditsID());
        for(Actor actor : credits.getCast()) {
            System.out.println(actor.getName());
        }
    }

}
