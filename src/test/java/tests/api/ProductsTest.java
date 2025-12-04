package tests.api;

import base.BaseTestApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.client.ProductsApiClient;
import pageobjects.api.products.Product;
import pageobjects.api.products.ResponseProducts;
import testUtils.ApiTestUtils;
import assertions.api.ProductsAssertions;
import testUtils.TestDataProvider;

public class ProductsTest extends BaseTestApi {
    final ProductsApiClient productsClient = new ProductsApiClient();

    @Test(testName = "API 1: Get All Products List", groups = {"API"})
    public void getAllProductList() {
        ResponseProducts responseProducts = productsClient.getAllProductList();
        SoftAssert softAssert = new SoftAssert();
        ProductsAssertions.assertResponseCode(responseProducts.getResponseCode(), 200);
        ProductsAssertions.assertAllProducts(responseProducts);
        ProductsAssertions.assertNoDuplicateIds(responseProducts, softAssert);
        softAssert.assertAll();
    }

    @Test(testName = "API 2: POST To All Products List", groups = {"API"})
    public void postToAllProductList() {
        ProductsAssertions.verifyMethodNotSupported(ProductsApiClient.productsApiPath, "POST", "405",
                "This request method is not supported.");
    }

    @Test(dataProvider = "apiSearchQueries", dataProviderClass = TestDataProvider.class,
            testName = "API 5: POST To Search Product", groups = {"API"})
    public void postToSearchProduct(String query, boolean expectEmpty) {
        SoftAssert softAssert = new SoftAssert();
        ResponseProducts searchProductResponse = productsClient.searchProduct(query);
        ProductsAssertions.assertResponseCode(searchProductResponse.getResponseCode(), 200);
        ProductsAssertions.assertProductsNotEmptyIfExpected(searchProductResponse, query, expectEmpty);
        ProductsAssertions.verifyDuplicateIds(searchProductResponse.getProducts(), Product::getId,
                Product::getName, softAssert);
        ProductsAssertions.assertProductFieldsAndRelevance(searchProductResponse, query);
    }

    @Test(testName = "API 6: POST To Search Product without search_product parameter", groups = {"API"})
    public void POSTToSearchProductWithoutParameter() {
        Response productListResponse = productsClient.searchProductWithoutParam();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ApiTestUtils.getValueFromJson(productListResponse, "responseCode"), "400");
        softAssert.assertEquals(ApiTestUtils.getValueFromJson(productListResponse, "message"),
                "Bad request, search_product parameter is missing in POST request.");
        softAssert.assertAll();
    }
}