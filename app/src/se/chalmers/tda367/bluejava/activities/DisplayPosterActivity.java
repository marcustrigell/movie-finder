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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_poster_activity);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");
        movieApi = new MovieApi();

        removeActionBar();
        loadImage();

        posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * Removes the action bar in order to enhance full screen experience
     */
    private void removeActionBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }

    /**
     * Loads the image and displays it in full screen
     */
    private void loadImage() {
        posterView = (ImageView) findViewById(R.id.fullImage);
        String url = movieApi.getCoverURL(movie.getPosterPath());
        Picasso.with(BlueJava.getContext()).load(url).into(posterView);
    }

}
