package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static io.restassured.parsing.Parser.JSON;

public class BaseTestApi {

    @BeforeClass
    public void setup() {
        // Apply base request specification for all tests
        RestAssured.requestSpecification = new ApiSpecBuilder().baseReq;
        // Apply base response specification for all tests
        RestAssured.responseSpecification = new ApiSpecBuilder().baseResp;
        // Handle cases where server returns JSON with incorrect Content-Type
        RestAssured.registerParser("text/html", JSON);
    }
}
