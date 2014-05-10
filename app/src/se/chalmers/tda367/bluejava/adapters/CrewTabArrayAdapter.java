package se.chalmers.tda367.bluejava.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayMovieActivity;
import se.chalmers.tda367.bluejava.models.Actor;
import se.chalmers.tda367.bluejava.models.CrewMember;

import java.util.List;

/**
 * Used to create a list item in the crew view.
 *
 * Created by marcus on 2014-05-10.
 */
public class CrewTabArrayAdapter extends BaseAdapter {

    private final List<CrewMember> crew;

    private final Context context;

    private final Activity activity;

    public CrewTabArrayAdapter(Context context,Activity activity ,List<CrewMember> crew) {

        this.context = context;

        this.activity = activity;

        this.crew = crew;
    }

    @Override
    public int getCount() {
        return crew.size();
    }

    @Override
    public Object getItem(int position) {
        return crew.get(position);
    }

    @Override
    public long getItemId(int position) {
        return crew.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final CrewMember crewMember = (CrewMember) getItem(position);

        if(convertView == null) {

            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.crew_tab_list_item, null);

            ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView jobTextView = (TextView) convertView.findViewById(R.id.job);

            String url = "https://image.tmdb.org/t/p/" + crewMember.getProfilePath();
            Picasso.with(context).load(url).into(coverImageView);

            nameTextView.setText(crewMember.getName());
            jobTextView.setText(crewMember.getJOB());

        }

        return convertView;
    }
}
