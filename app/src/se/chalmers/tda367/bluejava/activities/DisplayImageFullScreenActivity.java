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
import se.chalmers.tda367.bluejava.models.*;

/**
 * This class is used to display a movie cover in full screen
 */
public class DisplayImageFullScreenActivity extends Activity {

    private Movie movie;
    private Person person;
    private MovieApi movieApi;
    private ImageView posterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display_image_fullscreen_activity);

        Intent intent = getIntent();
        movieApi = new MovieApi();

        if(intent.hasExtra(BlueJava.EXTRA_MOVIE)) {
            movie = intent.getParcelableExtra(BlueJava.EXTRA_MOVIE);
            loadImage(movieApi.getCoverURL(movie.getPosterPath()));
        } else if(intent.hasExtra(BlueJava.EXTRA_PERSON)) {
            person = intent.getParcelableExtra(BlueJava.EXTRA_PERSON);
            loadImage(movieApi.getCoverURL(person.getProfilePath()));
        }

        removeActionBar();

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
    private void loadImage(String url) {
        posterView = (ImageView) findViewById(R.id.fullImage);
        Picasso.with(BlueJava.getContext()).load(url).into(posterView);
    }

}