package tests.api;

import base.BaseTestApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.products.Product;
import pageobjects.api.products.products.ResponseProducts;
import testUtils.ApiTestUtils;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class ProductsTest extends BaseTestApi {
    private final String productsApiPath = "productsList";
    private final String searchProductApiPath = "searchProduct";

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"top", false},
                {"jean", false},
                {"tshirt", false},
                {"abrakadabra", true},  // expecting empty list
                {"", false},            // behaves like getAllProducts → non-empty
                {null, false}           // behaves like getAllProducts → non-empty
        };
    }

    @Test(testName = "API 1: Get All Products List")
    public void getAllProductList() {

        ResponseProducts responseProducts = given()
                .when().get(productsApiPath)
                .then()
                .extract().as(ResponseProducts.class);

        Assert.assertEquals(responseProducts.getResponseCode(), 200, "Response code should be 200");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseProducts.getProducts().isEmpty(), "Product list should not be empty");

        // Check for duplicate IDs
        ApiTestUtils.verifyDuplicateIds(responseProducts.getProducts(), Product::getId,
                Product::getName, softAssert);


        for (Product p : responseProducts.getProducts()) {
            softAssert.assertTrue(Arrays.asList("Women", "Men", "Kids").contains(p.getCategory().getUsertype().getUsertype()),
                    "Usertype is invalid: " + p.getCategory().getUsertype().getUsertype());
        }

        softAssert.assertAll();
    }

    @Test(testName = "API 2: POST To All Products List")
    public void postToAllProductList() {
        ApiTestUtils.verifyMethodNotSupported(productsApiPath, "POST", "405",
                "This request method is not supported.");
    }

    @Test(dataProvider = "searchQueries", testName = "API 5: POST To Search Product")
    public void postToSearchProduct(String query, boolean expectEmpty) {
        ResponseProducts searchProductResponse = given()
                .contentType(ContentType.URLENC)
                .formParam("search_product", query)
                .when().post(searchProductApiPath)
                .then().extract().as(ResponseProducts.class);

        SoftAssert softAssert = new SoftAssert();

        // 1. Check responseCode = 200
        softAssert.assertEquals(searchProductResponse.getResponseCode(), 200, "Response code should be 200");

        // 2. Check empty vs non-empty list based on expectation
        if (expectEmpty) {
            softAssert.assertTrue(searchProductResponse.getProducts().isEmpty(),
                    "Expected empty result for query: " + query);
        } else {
            softAssert.assertFalse(searchProductResponse.getProducts().isEmpty(),
                    "Expected non-empty result for query: " + query);

            ApiTestUtils.verifyDuplicateIds(searchProductResponse.getProducts(), Product::getId,
                    Product::getName, softAssert);

            // 3. Validate product fields
            for (Product p : searchProductResponse.getProducts()) {
                softAssert.assertNotNull(p.getName(), "Product name is null");
                softAssert.assertNotNull(p.getPrice(), "Product price is null");
                softAssert.assertNotNull(p.getBrand(), "Product brand is null");
                softAssert.assertNotNull(p.getCategory(), "Product category is null");

                // 4. Flexible relevance check if query is provided
                if (query != null && !query.isEmpty()) {
                    String normalizedQuery = query.toLowerCase().replaceAll("[\\s&-]", "");
                    String normalizedName = p.getName().toLowerCase().replaceAll("[\\s&-]", "");
                    String normalizedCategory = p.getCategory().getCategory().toLowerCase().replaceAll("[\\s&-]", "");

                    softAssert.assertTrue(
                            normalizedName.contains(normalizedQuery) || normalizedCategory.contains(normalizedQuery),
                            "Product not relevant for query '" + query + "': " + p.getName() +
                                    " | Category: " + p.getCategory().getCategory()
                    );
                }
            }
        }

        softAssert.assertAll();
    }

    @Test (testName = "API 6: POST To Search Product without search_product parameter")
    public void POSTToSearchProductWithoutParameter(){
        Response productListResponse = given()
                .when().post(searchProductApiPath)
                .then()
                .extract().response();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ApiTestUtils.getValueFromJson(productListResponse, "responseCode"), "400");
        softAssert.assertEquals(ApiTestUtils.getValueFromJson(productListResponse, "message"),
                "Bad request, search_product parameter is missing in POST request.");

        softAssert.assertAll();
    }
}