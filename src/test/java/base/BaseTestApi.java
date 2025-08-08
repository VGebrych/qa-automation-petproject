package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Utils;

public class BaseTestApi {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Utils.getGlobalValue("baseURI");
    }
}