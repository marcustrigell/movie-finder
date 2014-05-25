package se.chalmers.tda367.bluejava.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * This class is responsible for managing a bunch of
 * Fragments that each represent a tab (and its' page).
 *
 * The fragments should already be created and only managed
 * by this class.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public TabsPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {

        super(fragmentManager);

        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }

    @Override
    public int getCount() {

        return this.fragments.size();

    }

}
