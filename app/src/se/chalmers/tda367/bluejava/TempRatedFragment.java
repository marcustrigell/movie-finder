package se.chalmers.tda367.bluejava;

/**
 * Created by iDavid on 2014-04-25.
 *
 * Temporary class for start view.
 * Something doesn't work when combining NavigationDrawer with ViewPager
 *
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TempRatedFragment extends Fragment {

	public TempRatedFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);

		return rootView;
	}
}
