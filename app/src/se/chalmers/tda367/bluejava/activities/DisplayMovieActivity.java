package se.chalmers.tda367.bluejava.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.adapters.MovieTabsAdapter;
import se.chalmers.tda367.bluejava.fragments.*;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;
import java.util.Vector;

public class DisplayMovieActivity extends FragmentActivity implements ActionBar.TabListener {

    private Movie movie;

    private ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.display_movie_activity);

        Intent intent = getIntent();

        movie = intent.getParcelableExtra("movie");

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

        fragments.add(MovieDetailsTabFragment.newInstance(movie));
        fragments.add(MovieCastTabFragment.newInstance(movie));
        fragments.add(MovieCrewTabFragment.newInstance(movie));
        fragments.add(MovieVideosTabFragment.newInstance(movie));

        pagerAdapter = new MovieTabsAdapter(super.getSupportFragmentManager(), fragments);
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
}
