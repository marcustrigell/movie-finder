package se.chalmers.tda367.bluejava.interfaces;

/**
 * An interface for all items in Navigation Drawer
 */
public interface NavigationDrawerItem {
	public String getLabel();
	public int getIcon();

	/**
	 * Returns the type of this item
	 */
	public int getType();
}
