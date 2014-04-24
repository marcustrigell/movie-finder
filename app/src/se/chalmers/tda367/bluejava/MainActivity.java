package se.chalmers.tda367.bluejava;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    public final static String EXTRA_MESSAGE = "se.chalmers.tda367.bluejava.MESSAGE";

	private CharSequence appTitle;

	/* -- Navigation Drawer -- */
	private DrawerLayout navDrawerLayout;
	private ListView navDrawerList;
	private ActionBarDrawerToggle navDrawerToggle;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter navDrawerAdapter;

	/* -- Navigation Drawer Title -- */
	private CharSequence navDrawerTitle;

	/* -- Navigation Drawer Items -- */
	private String[] navDrawerTitles;
	private TypedArray navDrawerIcons;

	/* -- View Pager -- */
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private String[] tabs = { "Recommended", "Top Rated", "Latest" };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		appTitle = getTitle();


		/* ------------------ Tobbe: I get exceptions with this code active  ------------------

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.pager);
        setContentView(viewPager);

        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        for (String tab_name : tabs) {
            bar.addTab(bar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }

        *//**
         * on swiping the viewpager make respective tab selected
         * *//*
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                bar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        ------------------------------------------------------------------------------------ */


		/* -- Navigation Drawer -- */

		// Set title for navigation drawer
		navDrawerTitle = getTitle();

		// Get titles for navigation drawer items
		navDrawerTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// Get icons for navigation drawer items
		navDrawerIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		navDrawerList = (ListView) findViewById(R.id.nav_drawer);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// Fill array with navDrawerItems
		// Home
		navDrawerItems.add(new NavDrawerItem(navDrawerTitles[0], navDrawerIcons.getResourceId(0, -1)));
		// Movies
		navDrawerItems.add(new NavDrawerItem(navDrawerTitles[1], navDrawerIcons.getResourceId(1, -1)));

		// Give back the typed array for later re-use
		navDrawerIcons.recycle();

		// Set the navigation drawer list adapter
		navDrawerAdapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		navDrawerList.setAdapter(navDrawerAdapter);

		// Enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		navDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		){
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(appTitle);

				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(navDrawerTitle);

				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		navDrawerLayout.setDrawerListener(navDrawerToggle);

	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

	public void searchMovies(View view) {
        Intent intent = new Intent(this, DisplayResultsActivity.class);
        EditText searchField = (EditText) findViewById(R.id.search_field);

		try {
			String message = searchField.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, message);

		} catch (NullPointerException e) {

		}

        startActivity(intent);
	}

	/**
	 * On selecting items in Action Bar
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Toggle navigation drawer on selecting action bar app icon/title
		if (navDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
			case R.id.action_search:
				// search action
				return true;
			/*case R.id.action_browse:
				// location found
				LocationFound();
				return true;
			case R.id.action_refresh:
				// refresh
				return true;*/
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If navigation drawer is opened, hide the action items
		boolean drawerOpen = navDrawerLayout.isDrawerOpen(navDrawerList);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		appTitle = title;
		getActionBar().setTitle(appTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		navDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		navDrawerToggle.onConfigurationChanged(newConfig);
	}
}
