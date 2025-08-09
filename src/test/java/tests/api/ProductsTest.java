package tests.api;

import base.BaseTestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.products.Product;
import pageobjects.api.products.products.ResponseProducts;
import utils.Utils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ProductsTest extends BaseTestApi {


    @Test
    public void getAllProductList() {

        ResponseProducts responseProducts = given().log().all()
                .when().get("api/productsList")
                .then()
                .extract().as(ResponseProducts.class);

        Assert.assertEquals(responseProducts.getResponseCode(), 200);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseProducts.getProducts().isEmpty(), "Product list should not be empty");

        // Check for duplicate IDs and valid usertype
        Set<Integer> uniqueIds = new HashSet<>();
        for (Product p : responseProducts.getProducts()) {
            softAssert.assertTrue(uniqueIds.add(p.getId()),
                    "Duplicate ID found: " + " (ID: " + p.getId() + "Name: " +p.getName() + ")");
            softAssert.assertTrue(Arrays.asList("Women", "Men", "Kids").contains(p.getCategory().getUsertype().getUsertype()),
                    "Usertype is invalid: " + p.getCategory().getUsertype().getUsertype());
        }

        softAssert.assertAll();
    }

    @Test
    public void postToAllProductList(){
        Response productListResponse = given().log().all()
                .when().post("api/productsList")
                .then()
                .extract().response();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Utils.getValueFromJson(productListResponse, "responseCode"), "405");
        softAssert.assertEquals(Utils.getValueFromJson(productListResponse, "message"),
                "This request method is not supported.");

        softAssert.assertAll();
    }
}