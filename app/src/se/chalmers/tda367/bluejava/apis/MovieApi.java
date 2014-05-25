package se.chalmers.tda367.bluejava.apis;

/**
 * Defines a contract with themoviedb.org's API
 * Used to build and return correct URI:s to the application.
 */
public class MovieApi {

    private static final String API_KEY = "api_key=cc6a7faf3b05d8dbe33b9140b16841bd";

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String PUBLIC_BASE_URL = "https://www.themoviedb.org/movie/";

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    private static final String[] POSTER_SIZE =
            new String[]{
                "w92",
                "w154",
                "w185",
                "w342",
                "w500",
                "w780",
                "original"
            };

    public String getSearchMovieQuery(String title) {
        return finalizeMovieQuery("search/movie?query=" + title);
    }

    public String getMovieDetailsQuery(int id) {
        return finalizeMovieQuery("movie/" + id);
    }

    public String getMovieCreditsQuery(int id) {
        return finalizeMovieQuery("movie/" + id + "/credits");
    }

    public String getTopRatedMoviesQuery() {
        return finalizeMovieQuery("movie/top_rated");
    }

    public String getPopularMoviesQuery() {
        return finalizeMovieQuery("movie/popular");
    }

    public String getUpcomingMoviesQuery() {
        return finalizeMovieQuery("movie/upcoming");
    }

    public String getPublicUrl(int id) {
        return PUBLIC_BASE_URL + id;
    }

    public String getMovieVideosQuery(int id) {
        return finalizeMovieQuery("movie/" + id + "/videos");
    }

	public String getPersonDetailsQuery(int id) {
		return finalizeMovieQuery("person/" + id);
	}

    /**
     * Adds beginning and ending to the query before returning it
     */
    public String finalizeMovieQuery(String query) {
        return BASE_URL + query + (query.contains("?") ? "&" : "?") + API_KEY;
    }

    public String getThumbnailURL(String posterPath) {
        return IMAGE_BASE_URL + POSTER_SIZE[0] + posterPath;
    }

    public String getCoverURL(String posterPath) {
        return IMAGE_BASE_URL + POSTER_SIZE[2] + posterPath;
    }

}