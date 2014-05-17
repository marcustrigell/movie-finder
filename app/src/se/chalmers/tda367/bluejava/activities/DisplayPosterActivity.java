package se.chalmers.tda367.bluejava.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.apis.MovieApi;
import se.chalmers.tda367.bluejava.models.BlueJava;
import se.chalmers.tda367.bluejava.models.Movie;

/**
 * Created by axelniklasson on 2014-05-17.
 */
public class DisplayPosterActivity extends Activity {

    private Movie movie;
    private MovieApi movieApi;
    private ImageView posterView;
    private Intent showDetailsIntent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_poster_activity);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");

        movieApi = new MovieApi();

        removeActionBar();
        loadImage();

        showDetailsIntent = new Intent(this, DisplayMovieActivity.class);

        posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDetailsIntent.putExtra("movie", movie);
                //startActivity(showDetailsIntent);
                finish();
            }
        });

    }

    private void removeActionBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }

    private void loadImage() {
        posterView = (ImageView) findViewById(R.id.fullImage);
        String url = movieApi.getCoverURL(movie.getPosterPath());
        Picasso.with(BlueJava.getContext()).load(url).into(posterView);
    }

}
