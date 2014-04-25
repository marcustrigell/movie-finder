package se.chalmers.tda367.bluejava;

/**
 * Created by iDavid on 2014-04-24.
 *
 * Represents an object in the navigation drawer list.
 *
 * A NavDrawerItem has a Title and an Icon in front of it
 */
public class NavDrawerItem {

	private String title;
	private int icon;

	public NavDrawerItem() {}

	public NavDrawerItem(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}

	public String getTitle() {
		return this.title;
	}

	public int getIcon() {
		return this.icon;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setIcon(int newIcon) {
		this.icon = newIcon;
	}

}
