package se.chalmers.tda367.bluejava.fragments;

public class TabFragmentMainPopular extends TabFragmentMain {
    public static TabFragmentMainPopular newInstance() {
        return new TabFragmentMainPopular();
    }

    @Override
    public void sendHttpGetRequest() {
        httpHandler.get(movieApi.getPopularMoviesQuery(), this);
    }
}
