package se.chalmers.tda367.bluejava.adapters;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.fragments.MovieCastTabFragment;
import se.chalmers.tda367.bluejava.fragments.MovieCrewTabFragment;
import se.chalmers.tda367.bluejava.fragments.MovieDetailsTabFragment;

public class MovieTabsAdapter extends FragmentPagerAdapter
        implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    private Movie movie;

    private Activity activity;

    public MovieTabsAdapter(FragmentManager fragmentManager, Activity activity, Movie movie) {
        super(fragmentManager);

        this.activity = activity;

        this.movie = movie;
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new MovieDetailsTabFragment(activity, movie);
            case 1:
                return new MovieCastTabFragment(activity, movie);
            case 2:
                return new MovieCrewTabFragment(activity, movie);
        }

        return null;
    }

    @Override
    public int getCount() {
        // Equal to number of tabs
        return 3;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
