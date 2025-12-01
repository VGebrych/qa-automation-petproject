package testUtils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "apiSearchQueries")
    public static Object[][] apiSearchQueries() {
        return new Object[][]{
                {"top", false},
                {"jean", false},
                {"tshirt", false},
                {"abrakadabra", true},  // expecting empty list
                {"", false},            // behaves like getAllProducts → non-empty
                {null, false}           // behaves like getAllProducts → non-empty
        };
    }

    @DataProvider(name = "uiSearchQueries")
    public static Object[][] uiSearchQueries() {
        return new Object[][]{
                {"top", false},
                {"jean", false},
                {"tshirt", false},
                {"abrakadabra", true},  // expecting empty list
        };
    }
}