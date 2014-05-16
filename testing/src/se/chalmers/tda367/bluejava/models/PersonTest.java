package se.chalmers.tda367.bluejava.models;

import android.content.Context;
import android.os.Parcel;
import android.test.InstrumentationTestCase;
import dalvik.annotation.TestTargetClass;
import org.json.JSONObject;
import org.junit.Test;
import se.chalmers.tda367.bluejava.tests.R;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class PersonTest extends InstrumentationTestCase {

    private Person person, personFromParcel;
    private final String name = "Brad Pitt";
    private final int id = 287;
    private final String profilePath = "/kc3M04QQAuZ9woUvH3Ju5T7ZqG5.jpg";
	private final String alsoKnownAs = "William Bradley Pitt";
	private final String biography = "William Bradley \\\"Brad\\\" Pitt (born December 18, 1963 height 5' 11" +
			"\\\" (1,80 m)) is an American actor and film producer. Pitt has received two Academy Award " +
			"nominations and four Golden Globe Award nominations, winning one. He has been described as " +
			"one of the world's most attractive men, a label for which he has received substantial media " +
			"attention. Pitt began his acting career with television guest appearances, including a role " +
			"on the CBS prime-time soap opera Dallas in 1987. He later gained recognition as the cowboy " +
			"hitchhiker who seduces Geena Davis's character in the 1991 road movie Thelma &amp; Louise.  " +
			"Pitt's first leading roles in big-budget productions came with A River Runs Through It (1992) " +
			"and Interview with the Vampire (1994). He was cast opposite Anthony Hopkins in the 1994 drama " +
			"Legends of the Fall, which earned him his first Golden Globe nomination. In 1995 he gave " +
			"critically acclaimed performances in the crime thriller Seven and the science fiction film " +
			"12 Monkeys, the latter securing him a Golden Globe Award for Best Supporting Actor and an " +
			"Academy Award nomination.\\n\\nFour years later, in 1999, Pitt starred in the cult hit Fight " +
			"Club. He then starred in the major international hit as Rusty Ryan in Ocean's Eleven (2001) " +
			"and its sequels, Ocean's Twelve (2004) and Ocean's Thirteen (2007). His greatest commercial " +
			"successes have been Troy (2004) and Mr. &amp; Mrs. Smith (2005).\\n\\nPitt received his second " +
			"Academy Award nomination for his title role performance in the 2008 film The Curious Case of " +
			"Benjamin Button. Following a high-profile relationship with actress Gwyneth Paltrow, Pitt was " +
			"married to actress Jennifer Aniston for five years. Pitt lives with actress Angelina Jolie in " +
			"a relationship that has generated wide publicity. He and Jolie have six children—Maddox, Pax, " +
			"Zahara, Shiloh, Knox, and Vivienne.\\n\\nSince beginning his relationship with Jolie, he has " +
			"become increasingly involved in social issues both in the United States and internationally. " +
			"Pitt owns a production company named Plan B Entertainment, whose productions include the 2007 " +
			"Academy Award winning Best Picture, The Departed.\\n\\nDescription above from the Wikipedia " +
			"article Brad Pitt, licensed under CC-BY-SA, full list of contributors on Wikipedia.";
	private final String birthday = "1963-12-18";
	private final String deathday = "";
	private final String placeOfBirth = "Shawnee - Oklahoma - USA";
	private final double popularity = 8.87835694812445;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        String json = context.getString(R.string.person);
        person = new Actor(new JSONObject(json));
        Parcel parcel = Parcel.obtain();
        person.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        personFromParcel = Actor.CREATOR.createFromParcel(parcel);
    }

    @Test
    public void testParcelable() {
        assertEquals(person.describeContents(), personFromParcel.describeContents());
    }

    @Test
    public void testGetName() {
        assertEquals(name, person.getName());
    }

    @Test
    public void testGetID() {
        assertEquals(id, person.getID());
    }

    @Test
    public void testGetProfilePath() {
        assertEquals(profilePath, person.getProfilePath());
    }

	@Test
	public void testGetAlsoKnownAs() {
		assertEquals(alsoKnownAs, person.getAlsoKnownAs());
	}

	@Test
	public void testGetBiography() {
		assertEquals(biography, person.getBiography());
	}

	@Test
	public void	testGetBirthday() {
		assertEquals(birthday, person.getBirthday());
	}

	@Test
	public void testGetDeathday() {
		assertEquals(deathday, person.getDeathday());
	}

	@Test
	public void testGetPlaceOfBirth() {
		assertEquals(placeOfBirth, person.getPlaceOfBirth());
	}

	@Test
	public void testGetPopularity() {
		assertEquals(popularity, person.getPopularity());
	}

}
