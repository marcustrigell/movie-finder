package se.chalmers.tda367.bluejava.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayPersonActivity;
import se.chalmers.tda367.bluejava.apis.MovieApi;
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

    /**
     * Converts an old view in a parent view to a new one.
     * @param position The position of the view in the parent.
     * @param convertView The view which is to be converted.
     * @param parent The parent view.
     * @return The view which is to be used.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Actor actor = (Actor) getItem(position);

        if(convertView == null) {

            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.cast_tab_list_item, null);

            /* Find the right view pieces to change. */
            ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView characterTextView = (TextView) convertView.findViewById(R.id.character);

            /* Load the picture into the image view. */
            MovieApi movieApi = new MovieApi();
            String url = movieApi.getCoverURL(actor.getProfilePath());
            Picasso.with(context).load(url).into(coverImageView);

            /* Set the correct text to the text fields. */
            nameTextView.setText(actor.getName());
            characterTextView.setText(actor.getCharacter());

            /* Show person details when clicked. */
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, DisplayPersonActivity.class);
                    intent.putExtra("person", actor);
                    activity.startActivity(intent);
                }
            });

        }

        return convertView;
    }
}
