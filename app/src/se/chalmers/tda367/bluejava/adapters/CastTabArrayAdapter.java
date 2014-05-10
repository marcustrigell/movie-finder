package se.chalmers.tda367.bluejava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class CastTabArrayAdapter extends ArrayAdapter<Actor> {

    private final List<Actor> cast;

    private final DisplayMovieActivity displayMovieActivity;

    private final int textViewResourceId;

    public CastTabArrayAdapter(DisplayMovieActivity displayMovieActivity,
                                      int textViewResourceId, List<Actor> cast) {

        super(displayMovieActivity, textViewResourceId, cast);

        this.displayMovieActivity = displayMovieActivity;

        this.textViewResourceId = textViewResourceId;

        this.cast = cast;
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(textViewResourceId, null);
        }

        final Actor actor = cast.get(position);

        if (convertView != null) {
            ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView characterTextView = (TextView) convertView.findViewById(R.id.character);

            String url = "https://image.tmdb.org/t/p/" + actor.getProfilePath();
            Picasso.with(displayMovieActivity).load(url).into(coverImageView);

            nameTextView.setText(actor.getName());
            characterTextView.setText(actor.getCharacter());

            // This is not used right now, can be used if you want an individual actor page.
            /*convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(displayMovieActivity, DisplayMovieActivity.class);

                    intent.putExtra("actor", actor);
                    displayMovieActivity.startActivity(intent);
                }
            });*/
        }

        return convertView;
    }
}
