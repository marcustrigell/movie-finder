package se.chalmers.tda367.bluejava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            double width = displaymetrics.widthPixels;

            int coverWidth = (int) Math.round(width / 3);
            int coverHeight = (int) Math.round(coverWidth * 1.5);

            GridView.LayoutParams imageViewSize = new GridView.LayoutParams(coverWidth, coverHeight);

            imageView.setLayoutParams(imageViewSize);

        } else {
            imageView = (ImageView) convertView;
        }

        MovieApi movieApi = new MovieApi();

        final Movie movie = getItem(position);

        String url = movieApi.getCoverURL(movie.getPosterPath());

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_drawer)
                .error(R.drawable.ic_drawer)
                .fit()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DisplayMovieActivity.class);
                String message = movie.getID();
                intent.putExtra("movie", movie);
                activity.startActivity(intent);
            }
        });

        return imageView;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
