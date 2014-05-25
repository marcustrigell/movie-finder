package se.chalmers.tda367.bluejava.interfaces;

/**
 * This Interface provides basic operations to
 * authenticate users with the Facebook Login.
 */
public interface FBAuthenticator {
    /**
     * This method should be called when a user
     * has successfully logged in to their Facebook account
     *
     * @param FBaccessToken the session token returned by Facebook
     */
    public void hasLoggedIn(String FBaccessToken);

    /**
     * This method should be called when a user
     * has logged out from their Facebook account
     */
    public void hasLoggedOut();
}
