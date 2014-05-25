package se.chalmers.tda367.bluejava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.tda367.bluejava.R;

/**
 * Represents a help view explaining how
 * the homescreen in the applications works
 * and how it should be used.
 */
public class HelpStartFragment extends HelpFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_start, container, false);
        return view;
    }

}
