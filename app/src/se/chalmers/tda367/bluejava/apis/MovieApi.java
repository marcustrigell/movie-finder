package se.chalmers.tda367.bluejava.apis;

public class MovieApi {

    private String apiKey, baseUrl, imageBaseUrl, youtubeBaseUrl;

    private String[] posterSizes;

    public MovieApi() {

        apiKey = "api_key=cc6a7faf3b05d8dbe33b9140b16841bd";

        baseUrl = "https://api.themoviedb.org/3/";

        imageBaseUrl = "https://image.tmdb.org/t/p/";

        posterSizes = new String[]{"w92", "w154", "w185", "w342", "w500", "w780", "original"};

        youtubeBaseUrl = "https://www.youtube.com/watch?v=";
    }

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

    public String getMovieImagesQuery(int id) {
        return finalizeMovieQuery("movie/" + id + "/images");
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
        return baseUrl + query + (query.contains("?") ? "&" : "?") + apiKey;
    }

    public String getThumbnailURL(String posterPath) {
        return imageBaseUrl + posterSizes[0] + posterPath;
    }

    public String getCoverURL(String posterPath) {
        return imageBaseUrl + posterSizes[2] + posterPath;
    }

    public String getYoutubeURL(String id) {
        return youtubeBaseUrl + id;
    }

}