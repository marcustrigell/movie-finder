package se.chalmers.tda367.bluejava.interfaces;

/**
 * Created by iDavid on 2014-04-25.
 *
 * An interface for all items in Navigation Drawer
 *
 */
public interface INavDrawerItem {
	public String getLabel();
	public int getIcon();

	/**
	 * Returns the type of this item
	 */
	public int getType();
}
