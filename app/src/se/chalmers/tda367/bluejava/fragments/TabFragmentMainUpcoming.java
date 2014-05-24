package se.chalmers.tda367.bluejava.fragments;

public class TabFragmentMainUpcoming extends TabFragmentMain {

    public static TabFragmentMainUpcoming newInstance() {
        return new TabFragmentMainUpcoming();
    }

    @Override
    public void sendHttpGetRequest() {
        httpHandler.get(movieApi.getUpcomingMoviesQuery(), this);
    }

}
