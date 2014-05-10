package se.chalmers.tda367.bluejava.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.List;

/**
 * Used to create a list item in the cast view.
 *
 * Created by marcus on 2014-05-10.
 */
public class CastTabArrayAdapter extends BaseAdapter {

    private final Context context;

    private final List<Actor> cast;

    private final Activity activity;

    public CastTabArrayAdapter(Context context, Activity activity, List<Actor> cast) {

        this.activity = activity;

        this.context = context;

        this.cast = cast;
   }

    @Override
    public int getCount() {
        return cast.size();
    }

    @Override
    public Object getItem(int position) {
        return cast.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cast.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Actor actor = (Actor)getItem(position);

        ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView characterTextView = (TextView) convertView.findViewById(R.id.character);

        String url = "https://image.tmdb.org/t/p/" + actor.getProfilePath();
        Picasso.with(context).load(url).into(coverImageView);

        nameTextView.setText(actor.getName());
        characterTextView.setText(actor.getCharacter());

        return convertView;
    }
}
