package se.chalmers.tda367.bluejava.interfaces;

public interface FBAuthenticator {
    public void hasLoggedIn(String FBaccessToken);
    public void hasLoggedOut();
}
