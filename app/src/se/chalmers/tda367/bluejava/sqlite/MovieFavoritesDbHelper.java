package se.chalmers.tda367.bluejava.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.json.JSONException;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.LinkedList;
import java.util.List;

public class MovieFavoritesDbHelper extends SQLiteOpenHelper {

    public MovieFavoritesDbHelper(Context context) {
        super(context, MovieContract.DATABASE_NAME, null, MovieContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieContract.FavoriteTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MovieContract.FavoriteTable.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addMovie(Movie movie){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(movie);

        db.insert(
                MovieContract.FavoriteTable.TABLE_NAME,
                null,
                values);

        db.close();
    }

    public Movie getMovie(int id) throws JSONException {

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder = MovieContract.FavoriteTable.KEY_POPULARITY + " DESC";
        String[] selectionArgs = new String[] {String.valueOf(id)};

        // Build query
        Cursor cursor = db.query(
                MovieContract.FavoriteTable.TABLE_NAME,
                MovieContract.FavoriteTable.KEY_ARRAY,
                " id = ?",
                selectionArgs,
                null,
                null,
                sortOrder,
                null); //limit

        Movie movie = null;

        // If we got results get the first one
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            movie = cursorToMovie(cursor);
        }

        cursor.close();

        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();

        // Build the query
        String query = "SELECT * FROM " + MovieContract.FavoriteTable.TABLE_NAME;

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Go over each row, build a movie and add it to list
        if (cursor.moveToFirst()) {
            do {
                movies.add(cursorToMovie(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return movies;
    }

    public void deleteMovie(Movie movie) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        String[] selectionsArgs = new String[] { String.valueOf(movie.getID())};

        db.delete(MovieContract.FavoriteTable.TABLE_NAME,
                MovieContract.FavoriteTable.KEY_MOVIE_ID + " = ?",
                selectionsArgs);

        db.close();
    }

    public boolean isFavorite(int id) {

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder = MovieContract.FavoriteTable.KEY_POPULARITY + " DESC";
        String[] selectionArgs = new String[] {String.valueOf(id)};

        // Build query
        Cursor cursor = db.query(
                MovieContract.FavoriteTable.TABLE_NAME,
                MovieContract.FavoriteTable.KEY_ARRAY,
                " id = ?",
                selectionArgs,
                null,
                null,
                sortOrder,
                null); //limit

        return cursor.moveToFirst();
    }

    /**
     * Create a map of values, with column names as keys
     * @param movie The movie object to get values from
     * @return ContenValues map
     */
    private ContentValues getContentValues(Movie movie) {
        ContentValues values = new ContentValues();

        values.put(MovieContract.FavoriteTable.KEY_MOVIE_ID, movie.getID());
        values.put(MovieContract.FavoriteTable.KEY_TITLE, movie.getTitle());
        values.put(MovieContract.FavoriteTable.KEY_RELEASE_YEAR, movie.getReleaseYear());
        values.put(MovieContract.FavoriteTable.KEY_POPULARITY, movie.getPopularity());
        values.put(MovieContract.FavoriteTable.KEY_RATING, movie.getRating());
        values.put(MovieContract.FavoriteTable.KEY_VOTE_COUNT, movie.getVoteCount());
        values.put(MovieContract.FavoriteTable.KEY_POSTER_PATH, movie.getPosterPath());

        return values;
    }

    /**
     * Build movie from a cursor
     * @param cursor Result form db query
     * @return A new movie object
     */
    private Movie cursorToMovie(Cursor cursor) {
        int keyArraySize = MovieContract.FavoriteTable.KEY_ARRAY.length;
        String[] keys = new String[keyArraySize];

        for (int i = 0; i < keyArraySize; ++i) {
            keys[i] = cursor.getString(i);
        }

        return new Movie.Builder(keys).build();
    }

}
