package se.chalmers.tda367.bluejava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.tda367.bluejava.R;

/**
 * Represents a help view explaining how
 * the search feature in the applications works.
 */
public class HelpSearchFragment extends HelpFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_search, container, false);
        return view;
    }

}
