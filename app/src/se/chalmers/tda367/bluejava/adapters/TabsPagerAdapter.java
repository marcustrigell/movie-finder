package se.chalmers.tda367.bluejava.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

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
