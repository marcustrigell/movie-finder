package se.chalmers.tda367.bluejava.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.TabsPagerAdapter;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMovieCast;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMovieCrew;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMovieDetails;
import se.chalmers.tda367.bluejava.fragments.TabFragmentMovieVideos;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;
import java.util.Vector;

/**
 * Shows all information available on a movie in 4 tabs
 */
public class DisplayMovieActivity extends FragmentActivity implements ActionBar.TabListener {

    private Movie movie;

    private ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.display_movie_activity);

        Intent intent = getIntent();

        movie = intent.getParcelableExtra(BlueJava.EXTRA_MOVIE);

        setupTabs(savedInstanceState);
    }

    private void setupTabs(Bundle savedInstanceState) {

        final ActionBar actionBar = getActionBar();

        if (actionBar == null) {
            return;
        }

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        //actionBar.setDisplayShowHomeEnabled(false);

        String[] tabs = { "Details", "Cast", "Crew", "Videos" };

        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(TabFragmentMovieDetails.newInstance(movie));
        fragments.add(TabFragmentMovieCast.newInstance(movie));
        fragments.add(TabFragmentMovieCrew.newInstance(movie));
        fragments.add(TabFragmentMovieVideos.newInstance(movie));

        pagerAdapter = new TabsPagerAdapter(super.getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.pager);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		// Associate the searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}
}
