package se.chalmers.tda367.bluejava;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;


/**
 * A test suite containing all tests for the application.
 */
public class AllTests extends TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(AllTests.class).includePackages("se.chalmers.tda367.bluejava.models").build();
    }
}