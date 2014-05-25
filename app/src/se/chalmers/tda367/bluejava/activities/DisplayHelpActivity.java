package se.chalmers.tda367.bluejava.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.fragments.HelpFragment;

/**
 * Created by axelniklasson on 2014-05-25.
 */
public class DisplayHelpActivity extends FragmentActivity {

    private static final int NUMBER_OF_PAGES = 3;

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
    }

    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            return HelpFragment.newInstance(index);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }
    }
}