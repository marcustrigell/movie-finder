package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private final Context context;

    private final Activity activity;

    private final List<Movie> movies;

    public ImageAdapter(Context context, Activity activity, List<Movie> movies) {

        this.context = context;

        this.activity = activity;

        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        double width = displaymetrics.widthPixels;

        int coverWidth = (int) Math.round(width / 3);
        int coverHeight = (int) Math.round(coverWidth * 1.5);

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(coverWidth,
                    coverHeight));

        } else {
            imageView = (ImageView) convertView;
        }

        // Get the image URL for the current position.
        MovieApi movieApi = new MovieApi();
        String url = movieApi.getCoverURL(getItem(position));

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_drawer)
                .error(R.drawable.ic_drawer)
                .fit()
                .into(imageView);

        return imageView;
    }

    @Override public int getCount() {
        return movies.size();
    }

    @Override public String getItem(int position) {
        return movies.get(position).getPosterPath();
    }

    @Override public long getItemId(int position) {
        return 0;
    }
}
