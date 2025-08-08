package tests.api;

import base.BaseTestApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.Product;
import pageobjects.api.products.ResponseProducts;
import utils.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;

public class ProductsTest extends BaseTestApi {


    @Test
    public void getAllProductList() throws JsonProcessingException {

        ResponseProducts responseProducts = given().log().all()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when().get("api/productsList")
                .then()
                .parser("text/html", JSON)  // Force RestAssured to treat 'text/html' responses as JSON for deserialization
                .statusCode(200)
                .extract().as(ResponseProducts.class);

        // Validate the response code
        Assert.assertEquals(responseProducts.getResponseCode(), 200);

        // Validate that the product list is not empty
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseProducts.getProducts().isEmpty(), "Product list should not be empty");

        // Check for duplicate IDs and valid usertype
        Set<Integer> uniqueIds = new HashSet<>();
        for (Product p : responseProducts.getProducts()) {
            softAssert.assertTrue(uniqueIds.add(p.getId()), "Duplicate ID found: " + p.getId());
            softAssert.assertTrue(Arrays.asList("Women", "Men", "Kids").contains(p.getCategory().getUsertype().getUsertype()),
                    "Usertype is invalid: " + p.getCategory().getUsertype().getUsertype());
        }

        softAssert.assertAll();
    }

    @Test
    public void PostToAllProductList(){
        Response productListResponse = given().log().all()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when().post("api/productsList")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(Utils.getValueFromJson(productListResponse, "responseCode"), "405");
        Assert.assertEquals(Utils.getValueFromJson(productListResponse, "message"),
                "This request method is not supported.");
    }
}