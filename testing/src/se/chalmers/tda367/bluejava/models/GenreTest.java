package se.chalmers.tda367.bluejava.models;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.tests.R;

/**
 * Created by axelniklasson on 2014-05-13.
 */
public class GenreTest extends InstrumentationTestCase {

    private Genre genre, genreFromParcel;
    private final int ID = 28;
    private final String NAME = "Action";

    public void setUp() throws Exception {
        Context context = getInstrumentation().getContext();
        String genres = context.getString(R.string.genres);
        genre = new Genre(new JSONObject(genres));

        Parcel parcel = Parcel.obtain();
        genre.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        genreFromParcel = Genre.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testParcelable() {
        assertEquals(genre.describeContents(), genreFromParcel.describeContents());
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(ID, genre.getID());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(NAME, genre.getName());
    }

    //TODO: implement test for Genre.jsonToListOfGenres
}
