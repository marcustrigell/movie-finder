package se.chalmers.tda367.bluejava.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.MainTabsAdapter;
import se.chalmers.tda367.bluejava.adapters.NavDrawerAdapter;
import se.chalmers.tda367.bluejava.interfaces.INavDrawerItem;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.NavDrawerItem;
import se.chalmers.tda367.bluejava.models.NavDrawerSection;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    public final static String EXTRA_MESSAGE = "se.chalmers.tda367.bluejava.MESSAGE";

	private CharSequence appTitle;

	/* -- Navigation Drawer -- */
	private DrawerLayout navDrawerLayout;
	private ListView navDrawerList;
	private ActionBarDrawerToggle navDrawerToggle;
	private ArrayList<INavDrawerItem> navDrawerItems;
	private NavDrawerAdapter navDrawerAdapter;

	/* -- Navigation Drawer Title -- */
	private CharSequence navDrawerTitle;

	/* -- Navigation Drawer Items -- */
	private String[] navDrawerTitles;
	private TypedArray navDrawerIcons;

    private ViewPager viewPager;

    private boolean isLoggedIn;

    private String fbAccessToken;

    private LoginFragment loginFragment;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        setupTabs(savedInstanceState);

        if (savedInstanceState == null) {
            loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, loginFragment)
                    .commit();
        } else {
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

        setupLayout();

		appTitle = getTitle();
	}

    private void setupTabs(Bundle savedInstanceState) {

        final ActionBar actionBar = getActionBar();

        if (actionBar == null) {
            return;
        }

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        viewPager = (ViewPager) findViewById(R.id.mainPager);
        MainTabsAdapter mainTabsAdapter = new MainTabsAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mainTabsAdapter);

        String[] tabs = { "Popular", "Upcoming" };

        for (String tab : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab).setTabListener(this));
        }

        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }

        /**
         * Make respective tab selected when swiping the viewpager
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public void hasLoggedIn(String FBaccessToken) {
        this.fbAccessToken = FBaccessToken;

        if (!isLoggedIn) {
            invalidateOptionsMenu();
            BlueJava blueJavaApplication = (BlueJava) getApplication();
            blueJavaApplication.setUserFBAuthToken(FBaccessToken);
            Toast.makeText(this, getString(R.string.fb_logged_in), Toast.LENGTH_SHORT).show();
            isLoggedIn = true;
        }
    }

    public void hasLoggedOut() {
        invalidateOptionsMenu();
        fbAccessToken = null;
        BlueJava blueJavaApplication = (BlueJava) getApplication();
        blueJavaApplication.setUserFBAuthToken(null);
        Toast.makeText(this, getString(R.string.fb_logged_out), Toast.LENGTH_SHORT).show();
        isLoggedIn = false;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    private void setupLayout() {

        /* -- Navigation Drawer -- */

        // Set title for navigation drawer
        navDrawerTitle = getResources().getString(R.string.title_nav_drawer);

        // Get titles for navigation drawer items
        navDrawerTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // Get icons for navigation drawer items
        navDrawerIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawerList = (ListView) findViewById(R.id.nav_drawer);

        navDrawerItems = new ArrayList<INavDrawerItem>();

/* ------------------------------------------------------------------------------------------------------------ */

		/* This part should be implemented smarter */

        // Fill array with navDrawerItems and navDrawerSections
        // Home
        navDrawerItems.add(new NavDrawerItem(navDrawerTitles[0], navDrawerIcons.getResourceId(0, -1)));

        // Section - Browse Movies
        navDrawerItems.add(new NavDrawerSection(navDrawerTitles[1]));

        // Latest
        navDrawerItems.add(new NavDrawerItem(navDrawerTitles[2], navDrawerIcons.getResourceId(2, -1)));

        // Top Rated
        navDrawerItems.add(new NavDrawerItem(navDrawerTitles[3], navDrawerIcons.getResourceId(3, -1)));

        // Recommended
        navDrawerItems.add(new NavDrawerItem(navDrawerTitles[4], navDrawerIcons.getResourceId(4, -1)));

        // Section - Your Profile
        navDrawerItems.add(new NavDrawerSection(navDrawerTitles[5]));

        if (isLoggedIn) {
            // Favorites
            navDrawerItems.add(new NavDrawerItem(navDrawerTitles[6], navDrawerIcons.getResourceId(6, -1)));

            // Seen
            navDrawerItems.add(new NavDrawerItem(navDrawerTitles[7], navDrawerIcons.getResourceId(7, -1)));
        }



/* ------------------------------------------------------------------------------------------------------------ */

        // Recycle the typed array for later re-use (necessary for some reason)
        navDrawerIcons.recycle();

        // Set the navigation drawer list adapter
        navDrawerAdapter = new NavDrawerAdapter(getApplicationContext(), navDrawerItems);
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

                // Calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(navDrawerTitle);

                // Calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        navDrawerLayout.setDrawerListener(navDrawerToggle);
        navDrawerList.setOnItemClickListener(new NavigationDrawerClickListener());
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

		/*
		Associate the searchable configuration with the SearchView by calling setSearchableInfo(SearchableInfo)

		The call to getSearchableInfo() obtains a SearchableInfo object that is created from
		the searchable configuration XML file. When the searchable configuration is correctly
		associated with your SearchView, the SearchView starts an activity with the
		ACTION_SEARCH intent when a user submits a query.
		*/
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * On selecting items in Action Bar
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Toggle navigation drawer on selecting action bar app icon/title
		if (navDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
			case R.id.action_settings:
				openSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when settings button is clicked.
	 * Starts SettingsActivity.
	 */
	public void openSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
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
	 * onPostCreate() and onConfigurationChanged()
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

	/**
	 * Navigation drawer item click listener
	 * */
	private class NavigationDrawerClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// Display view for selected nav drawer item
			displayView(position);
		}
	}

	/**
	 * Displaying fragment view for selected navigation drawer list item
	 */

	// Using this for testing purposes.

    private void displayView(int position) {

        Intent intent = new Intent(this, DisplayResultsActivity.class);

        navDrawerList.setItemChecked(position, true);
        navDrawerList.setSelection(position);
        setTitle(navDrawerTitles[position]);
        navDrawerLayout.closeDrawer(navDrawerList);

        switch(position) {
            case 2 :
                intent.putExtra(EXTRA_MESSAGE, "upcoming");
                startActivity(intent);
                break;
            case 3 :
                intent.putExtra(EXTRA_MESSAGE, "popular");
                startActivity(intent);
                break;
            case 4 :
                intent.putExtra(EXTRA_MESSAGE, "top_rated");
                startActivity(intent);
                break;
            default:
                //else
                break;
        }
		// Update the main content by replacing fragments
		/*Fragment fragment = null;

		if (position == 0) {
			fragment = new HomeFragment();
		} else if (position == 1) {
			;
		} else if (position == 2) {
			fragment = new TempLatestFragment();
		} else if (position == 3) {
			fragment = new TempPopularFragment();
		} else if (position == 4) {
			fragment = new TempRatedFragment();
		} else {
			// For now...
			fragment = new HomeFragment();
		}
*/
		/* ------------- I tried this ------------- */

		// I tried making a query, but didn't succeed... ;)
		// My thought is working with "discover" in themoviedb-API somehow.

		/*Intent intent = new Intent(this, DisplayResultsActivity.class);

		if (position == 0) {
			fragment = new HomeFragment();
		} else if (position == 1) {
			;
		} else if (position == 2) {
			intent.putExtra(EXTRA_MESSAGE, "latest");
		} else if (position == 3) {
			intent.putExtra(EXTRA_MESSAGE, "popular");
		} else if (position == 4) {
			intent.putExtra(EXTRA_MESSAGE, "rated");
		}

		startActivity(intent);*/

		/* ------------- up to here ------------- */

		/*if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();


		} else {
			// Error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
*/
        // Update selected item and title, then close the drawer

	}
}
