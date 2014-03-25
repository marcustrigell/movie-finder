package se.chalmers.tda367.bluejava;

public class MovieApi {

    private String baseUrl, apiKey;

    public MovieApi() {

        // Maybe this one shouldn't be public?
        apiKey = "api_key=cc6a7faf3b05d8dbe33b9140b16841bd";

        baseUrl = "https://api.themoviedb.org/3/";
    }

    public String createMovieQuery(String title) {
        String query = baseUrl + "search/movie?query=" + title + "&" + apiKey;
        return query;
    }
}