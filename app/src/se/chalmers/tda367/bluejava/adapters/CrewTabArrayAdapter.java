package se.chalmers.tda367.bluejava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.activities.DisplayMovieActivity;
import se.chalmers.tda367.bluejava.models.CrewMember;

import java.util.List;

/**
 * Used to create a list item in the crew view.
 *
 * Created by marcus on 2014-05-10.
 */
public class CrewTabArrayAdapter extends ArrayAdapter<CrewMember> {

    private final List<CrewMember> crew;

    private final DisplayMovieActivity displayMovieActivity;

    private final int textViewResourceId;

    public CrewTabArrayAdapter(DisplayMovieActivity displayMovieActivity,
                               int textViewResourceId, List<CrewMember> crew) {

        super(displayMovieActivity, textViewResourceId, crew);

        this.displayMovieActivity = displayMovieActivity;

        this.textViewResourceId = textViewResourceId;

        this.crew = crew;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(textViewResourceId, null);
        }

        final CrewMember crewMember = crew.get(position);

        if (convertView != null) {
            ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView jobTextView = (TextView) convertView.findViewById(R.id.job);

            String url = "https://image.tmdb.org/t/p/" + crewMember.getProfilePath();
            Picasso.with(displayMovieActivity).load(url).into(coverImageView);

            nameTextView.setText(crewMember.getName());
            jobTextView.setText(crewMember.getJOB());

            // This is not used right now, can be used if you want an individual crewMember page.
            /*convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(displayMovieActivity, DisplayMovieActivity.class);

                    intent.putExtra("crewMember", crewMember);
                    displayMovieActivity.startActivity(intent);
                }
            });*/
        }

        return convertView;
    }
}
