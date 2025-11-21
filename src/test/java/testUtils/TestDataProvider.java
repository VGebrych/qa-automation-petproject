package testUtils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "searchQueries")
    public static Object[][] searchQueries() {
        return new Object[][]{
                {"top", false},
                {"jean", false},
                {"tshirt", false},
                {"abrakadabra", true},  // expecting empty list
                {"", false},            // behaves like getAllProducts → non-empty
                {null, false}           // behaves like getAllProducts → non-empty
        };
    }
}