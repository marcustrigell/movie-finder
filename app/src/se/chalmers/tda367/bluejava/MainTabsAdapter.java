package se.chalmers.tda367.bluejava;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainTabsAdapter extends FragmentPagerAdapter
        implements ActionBar.TabListener, ViewPager.OnPageChangeListener  {

    private Activity activity;

    public MainTabsAdapter(FragmentManager fragmentManager, Activity activity) {
        super(fragmentManager);

        this.activity = activity;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new MainPopularFragment(activity, R.layout.fragment_main_gridview);
            case 1:
                return new MainUpcomingFragment(activity, R.layout.fragment_main_gridview);
        }

        return null;
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
    public int getCount() {
        // number of tabs
        return 2;
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
