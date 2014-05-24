package se.chalmers.tda367.bluejava.interfaces;

import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * This interface provides basic database operations
 * on a movie database.
 */
public interface MovieDb {

    public void addMovie(Movie movie);

    public Movie getMovie(int id);

    public List<Movie> getAllMovies();

    public void deleteMovie(Movie movie);
}
