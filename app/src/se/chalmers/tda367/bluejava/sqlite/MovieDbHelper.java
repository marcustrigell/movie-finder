package se.chalmers.tda367.bluejava.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.json.JSONException;
import se.chalmers.tda367.bluejava.models.Movie;
import se.chalmers.tda367.bluejava.models.MovieContract;

import java.util.LinkedList;
import java.util.List;

public class MovieDbHelper extends SQLiteOpenHelper {


    public MovieDbHelper(Context context) {
        super(context, MovieContract.DATABASE_NAME, null, MovieContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieContract.MovieTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MovieContract.MovieTable.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addMovie(Movie movie){
        Log.d("addMovie", movie.toString());

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(movie);

        db.insert(
                MovieContract.MovieTable.TABLE_NAME,
                null,
                values);

        db.close();
    }

    public Movie getMovie(int id) throws JSONException {

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder = MovieContract.MovieTable.KEY_POPULARITY + " DESC";
        String[] selectionArgs = new String[] {String.valueOf(id)};

        // Build query
        Cursor cursor = db.query(
                MovieContract.MovieTable.TABLE_NAME,
                MovieContract.MovieTable.KEY_ARRAY,
                " id = ?",
                selectionArgs,
                null,
                null,
                sortOrder,
                null); //limit

        // If we got results get the first one
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Movie movie = cursorToMovie(cursor);

        Log.d("getMovie("+id+")", movie.toString());

        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();

        // Build the query
        String query = "SELECT * FROM " + MovieContract.MovieTable.TABLE_NAME;

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Go over each row, build a movie and add it to list
        if (cursor.moveToFirst()) {
            do {
                movies.add(cursorToMovie(cursor));
            } while (cursor.moveToNext());
        }

        Log.d("getAllMovies()", movies.toString());

        return movies;
    }

    public void deleteMovie(Movie movie) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        String[] selectionsArgs = new String[] { String.valueOf(movie.getID())};

        db.delete(MovieContract.MovieTable.TABLE_NAME,
                MovieContract.MovieTable.KEY_MOVIE_ID + " = ?",
                selectionsArgs);

        db.close();

        Log.d("deleteMovie", movie.toString());

    }

    /**
     * Create a map of values, with column names as keys
     * @param movie The movie object to get values from
     * @return ContenValues map
     */
    private ContentValues getContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieTable.KEY_MOVIE_ID, movie.getID());
        values.put(MovieContract.MovieTable.KEY_TITLE, movie.getTitle());
        values.put(MovieContract.MovieTable.KEY_RELEASE_YEAR, movie.getReleaseYear());
        values.put(MovieContract.MovieTable.KEY_POPULARITY, movie.getPopularity());
        values.put(MovieContract.MovieTable.KEY_RATING, movie.getRating());
        values.put(MovieContract.MovieTable.KEY_VOTE_COUNT, movie.getVoteCount());
        values.put(MovieContract.MovieTable.KEY_POSTER_PATH, movie.getPosterPath());

        return values;
    }

    /**
     * Build movie from a cursor
     * @param cursor Result form db query
     * @return A new movie object
     */
    private Movie cursorToMovie(Cursor cursor) {
        int keyArraySize = MovieContract.MovieTable.KEY_ARRAY.length;
        String[] keys = new String[keyArraySize];

        for (int i = 1; i < keyArraySize; ++i) {
            keys[i] = cursor.getString(i);
        }

        return new Movie.Builder(keys).build();
    }

}
