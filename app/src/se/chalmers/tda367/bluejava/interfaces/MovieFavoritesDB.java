package se.chalmers.tda367.bluejava.interfaces;

/**
 * This interface provides basic database operations
 * on a movie favorites database.
 */
public interface MovieFavoritesDB extends MovieDb {
    /**
     * Checks if a movie is already marked as favorite.
     */
    public boolean isFavorite(int id);
}
