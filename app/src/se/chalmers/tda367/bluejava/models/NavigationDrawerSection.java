package se.chalmers.tda367.bluejava.models;

import se.chalmers.tda367.bluejava.interfaces.NavigationDrawerItem;

/**
 * Represents a header of a section in the navigation drawer list.
 */
public class NavigationDrawerSection implements NavigationDrawerItem {

	public static final int SECTION_TYPE = 0;
	private String label;
	private int icon = -1;

	public NavigationDrawerSection(String label) {
		this.label = label;
	}

	@Override
	public int getType() {
		return SECTION_TYPE;
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
