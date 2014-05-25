package se.chalmers.tda367.bluejava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.interfaces.FbMovieSharer;
import se.chalmers.tda367.bluejava.models.Movie;
import java.util.List;

/**
 * This class takes a list of (favorite) movies
 * and displays them in a ListView. When an item
 * is pressed, the FbMovieSharer attached should be notified
 * that a movie has been chosen and also receive the actual
 * movie so that it can create a post message to Facebook about it.
 */
public class ShareFavoritesArrayAdapter extends BaseAdapter {

    private final List<Movie> movies;

    private final FbMovieSharer fbMovieSharer;

    private final Context context;

    public ShareFavoritesArrayAdapter(FbMovieSharer fbMovieSharer, Context context, List<Movie> movies) {
        this.fbMovieSharer = fbMovieSharer;
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Converts an old view in a parent view to a new one.
     *
     * @param position    The position of the view in the parent.
     * @param convertView The view which is to be converted.
     * @param parent      The parent view.
     * @return The view which is to be used.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie movie = (Movie) getItem(position);

        if (convertView == null) {

            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.display_results_list_item, null);
        }

        ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);

        final MovieApi movieApi = new MovieApi();
        String url = movieApi.getThumbnailURL(movie.getPosterPath());
        Picasso.with(context).load(url).into(coverImageView);

        titleTextView.setText(movie.toString());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fbMovieSharer.shareMovieToFB(movie);
            }
        });

        return convertView;
    }

}

