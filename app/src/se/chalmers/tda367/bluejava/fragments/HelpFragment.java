package se.chalmers.tda367.bluejava.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by axelniklasson on 2014-05-25.
 */
public abstract class HelpFragment extends Fragment {

    public static HelpFragment newInstance(int index) {

        switch(index) {
            case 0:
                HelpStartFragment helpStartFragment = new HelpStartFragment();
                return helpStartFragment;
            case 1:
                HelpSearchFragment helpSearchFragment = new HelpSearchFragment();
                return helpSearchFragment;
            case 2:
                HelpBrowseFragment helpBrowseFragment = new HelpBrowseFragment();
                return helpBrowseFragment;
            default:
                return null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}