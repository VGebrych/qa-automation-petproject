package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ProductsTest {

    @Test
    public void getAllProductList() {
        RestAssured.baseURI = "https://automationexercise.com/";

        Response productListResponse = given().log().all()
                .when().get("api/productsList")
                .then().statusCode(200).extract().response();




    }
}
