package se.chalmers.tda367.bluejava.interfaces;

/**
 * This Interface provides basic operations to
 * authenticate users with the Facebook Login.
 */
public interface FBAuthenticator {
    public void hasLoggedIn(String FBaccessToken);
    public void hasLoggedOut();
}
