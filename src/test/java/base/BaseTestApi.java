package base;

import base.filters.AllureRestAssuredFilter;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.parsing.Parser.JSON;

@Listeners({listeners.ApiTestListener.class})
public class BaseTestApi extends BaseTest {

    @BeforeClass
    public void setup() {
        // Apply base request specification for all tests
        RestAssured.requestSpecification = new ApiSpecBuilder().baseReq;

        // Apply base response specification for all tests
        RestAssured.responseSpecification = new ApiSpecBuilder().baseResp;

        // Handle cases where server returns JSON with incorrect Content-Type
        RestAssured.registerParser("text/html", JSON);

        // Register Allure filter for API logging
        RestAssured.filters(new AllureRestAssuredFilter());

    }
}