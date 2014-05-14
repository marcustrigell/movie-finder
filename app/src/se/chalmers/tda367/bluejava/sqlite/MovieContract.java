package se.chalmers.tda367.bluejava.sqlite;

import android.provider.BaseColumns;

public final class MovieContract {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Movies";

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MovieContract() {}

    // Inner class that defines the table contents
    public static abstract class FavoriteTable implements BaseColumns {

        // Do not allow this class to be instantiated
        private FavoriteTable() {}

        public static final String TABLE_NAME = "movie_favorites";
        public static final String KEY_MOVIE_ID = "id";
        public static final String KEY_TITLE = "title";
        public static final String KEY_RELEASE_YEAR = "release_year";
        public static final String KEY_POPULARITY = "popularity";
        public static final String KEY_RATING = "vote_average";
        public static final String KEY_VOTE_COUNT = "vote_count";
        public static final String KEY_POSTER_PATH = "poster_path";

        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY," +
                KEY_MOVIE_ID + TEXT_TYPE + COMMA_SEP +
                KEY_TITLE + TEXT_TYPE + COMMA_SEP +
                KEY_RELEASE_YEAR + TEXT_TYPE + COMMA_SEP +
                KEY_POPULARITY + TEXT_TYPE + COMMA_SEP +
                KEY_RATING + TEXT_TYPE + COMMA_SEP +
                KEY_VOTE_COUNT + TEXT_TYPE + COMMA_SEP +
                KEY_POSTER_PATH + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] KEY_ARRAY = {
            KEY_MOVIE_ID,
            KEY_TITLE,
            KEY_RELEASE_YEAR,
            KEY_POPULARITY,
            KEY_RATING,
            KEY_VOTE_COUNT,
            KEY_POSTER_PATH
        };
    }
}
