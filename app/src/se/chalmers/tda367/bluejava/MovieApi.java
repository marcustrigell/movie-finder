package se.chalmers.tda367.bluejava;

public class MovieApi {

    private String apiKey, baseUrl, imageBaseUrl;

    private String[] posterSizes;

    public MovieApi() {

        // Maybe this one shouldn't be public?
        apiKey = "api_key=cc6a7faf3b05d8dbe33b9140b16841bd";

        baseUrl = "https://api.themoviedb.org/3/";

        imageBaseUrl = "https://image.tmdb.org/t/p/";

        posterSizes = new String[]{"w92", "w154", "w185", "w342", "w500", "w780", "original"};

    }

    public String createMovieQuery(String title) {
        String query = baseUrl + "search/movie?query=" + title + "&" + apiKey;
        return query;
    }

    public String getImagePath(String posterPath) {
        return imageBaseUrl + posterSizes[0] + posterPath;
    }

}