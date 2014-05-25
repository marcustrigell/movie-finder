package se.chalmers.tda367.bluejava.interfaces;

import se.chalmers.tda367.bluejava.models.Movie;

/**
 * Classes implementing this interface
 * will be able to share movies to a user's wall
 */
public interface FbMovieSharer {

    /**
     * Takes a Movie an posts it to a logged in
     * user's wall on Facebook.
     */
    public void shareMovieToFB(Movie movie);

}
