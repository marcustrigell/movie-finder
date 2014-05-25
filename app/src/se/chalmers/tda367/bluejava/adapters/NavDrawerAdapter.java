package se.chalmers.tda367.bluejava.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import se.chalmers.tda367.bluejava.interfaces.NavigationDrawerItem;
import se.chalmers.tda367.bluejava.R;

import java.util.ArrayList;

/**
 * Created by iDavid on 2014-04-24.
 */
public class NavDrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavigationDrawerItem> navDrawerItems;

    public NavDrawerAdapter(Context context, ArrayList<NavigationDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        // Referrers to this position as a drawer item
        NavigationDrawerItem drawerItem = (NavigationDrawerItem) this.getItem(position);

        // Check the type of this drawer item
        if (drawerItem.getType() == se.chalmers.tda367.bluejava.models.NavigationDrawerItem.ITEM_TYPE) {
            view = getItemView(position, convertView, parent);
        } else {
            view = getSectionView(position, convertView, parent);
        }
        return view;
    }

    public View getItemView(int position, View convertView, ViewGroup viewGroup) {

        // Null check
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.nav_drawer_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.nav_drawer_item_icon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.nav_drawer_item_label);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        textTitle.setText(navDrawerItems.get(position).getLabel());

        return convertView;
    }

    public View getSectionView(int position, View convertView, ViewGroup viewGroup) {

        // Null check
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.nav_drawer_section, null);
        }

        TextView textTitle = (TextView) convertView.findViewById(R.id.nav_drawer_section_label);

        textTitle.setText(navDrawerItems.get(position).getLabel());

        return convertView;
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
    public int getViewTypeCount() {
        return 2;
    }
}
