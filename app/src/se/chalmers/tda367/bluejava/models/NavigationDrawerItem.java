package se.chalmers.tda367.bluejava.models;

/**
 * Created by iDavid on 2014-04-24.
 *
 * Represents an object in the navigation drawer list.
 */
public class NavigationDrawerItem implements se.chalmers.tda367.bluejava.interfaces.NavigationDrawerItem {

	public static final int ITEM_TYPE = 1 ;

	private String label;
	private int icon;

	public NavigationDrawerItem(String label, int icon) {
		this.label = label;
		this.icon = icon;
	}

	@Override
	public int getType() {
		return ITEM_TYPE;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
