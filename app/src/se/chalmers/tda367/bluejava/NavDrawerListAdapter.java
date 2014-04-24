package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iDavid on 2014-04-24.
 */
public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;

	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {

		// Null check
		if (view == null) {
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = mInflater.inflate(R.layout.nav_drawer_list_item, null);
		}

		//
		ImageView imgIcon = (ImageView) view.findViewById(R.id.icon);
		TextView textTitle = (TextView) view.findViewById(R.id.title);

		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
		textTitle.setText(navDrawerItems.get(position).getTitle());

		return view;
	}
}
