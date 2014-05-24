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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.NavDrawerAdapter;
import se.chalmers.tda367.bluejava.adapters.TabsPagerAdapter;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMainPopular;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMainUpcoming;
import se.chalmers.tda367.bluejava.interfaces.NavDrawerItem;
import se.chalmers.tda367.bluejava.models.NavDrawerSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    public final static String EXTRA_MESSAGE = "se.chalmers.tda367.bluejava.MESSAGE";

	private CharSequence appTitle;

	/* -- Navigation Drawer -- */
	private DrawerLayout navDrawerLayout;
	private ListView navDrawerList;
	private ActionBarDrawerToggle navDrawerToggle;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerAdapter navDrawerAdapter;

	/* -- Navigation Drawer Title -- */
	private CharSequence navDrawerTitle;

	/* -- Navigation Drawer Items -- */
	private String[] navDrawerTitles;
	private TypedArray navDrawerIcons;

	private ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        appTitle = getTitle();

        setupTabs(savedInstanceState);

        setupLayout();
	}

    private void setupTabs(Bundle savedInstanceState) {

        final ActionBar actionBar = getActionBar();

        if (actionBar == null) {
            return;
        }

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        String[] tabs = { "Popular", "Upcoming" };

        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(TabFragmentMainPopular.newInstance());
        fragments.add(TabFragmentMainUpcoming.newInstance());

        pagerAdapter = new TabsPagerAdapter(super.getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.mainPager);
        viewPager.setAdapter(pagerAdapter);

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

		// Set a custom shadow that overlays the main content when the drawer opens
		navDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // Fill array with navDrawerItems and navDrawerSections

        // Home
        navDrawerItems.add(new se.chalmers.tda367.bluejava.models.NavDrawerItem(navDrawerTitles[0], navDrawerIcons.getResourceId(0, -1)));

        // Section - Movies
        navDrawerItems.add(new NavDrawerSection(navDrawerTitles[1]));

			// Latest
			navDrawerItems.add(new se.chalmers.tda367.bluejava.models.NavDrawerItem(navDrawerTitles[2], navDrawerIcons.getResourceId(2, -1)));

			// Top Rated
			navDrawerItems.add(new se.chalmers.tda367.bluejava.models.NavDrawerItem(navDrawerTitles[3], navDrawerIcons.getResourceId(3, -1)));

			// Recommended
			navDrawerItems.add(new se.chalmers.tda367.bluejava.models.NavDrawerItem(navDrawerTitles[4], navDrawerIcons.getResourceId(4, -1)));

        // Section - Your Profile
        navDrawerItems.add(new NavDrawerSection(navDrawerTitles[5]));

            // Profile
            navDrawerItems.add(new se.chalmers.tda367.bluejava.models.NavDrawerItem(navDrawerTitles[6], navDrawerIcons.getResourceId(6, -1)));


        // Recycle the typed array for later re-use
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
            case 6 :
                intent = new Intent(this, DisplayProfileActivity.class);
                startActivity(intent);
                break;
            default:
                //else
                break;
        }
	}
}
