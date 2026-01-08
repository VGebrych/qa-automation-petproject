package tests.ui;

import assertions.ui.ProductsUiAssertions;
import base.BaseTestUI;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.client.ProductsApiClient;
import pageobjects.api.products.ResponseProducts;
import pageobjects.ui.pom.ProductDetailPage;
import pageobjects.ui.pom.ProductsPage;
import testUtils.TestDataProvider;

import java.util.List;

public class ProductsTest extends BaseTestUI {
    //18,19, 21,22 - Products tests will be implemented here
    @Test(testName = "TC08: Verify All Products and product detail page")
    public void verifyAllProductsAndProductDetailPage() {
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
        ProductsPage productsPage = homePage.header.clickProductsLink();
        softAssert.assertTrue(productsPage.isAtProductsPage(), "Not at All Products page");
        softAssert.assertTrue(productsPage.areProductsListVisible(), "Products are not visible");
        ProductDetailPage productDetailsPage = productsPage.clickOnFirstProductViewButton();
        softAssert.assertTrue(productDetailsPage.isAtProductDetailPage());
        productDetailsPage.verifyProductDetailsVisible(softAssert);
        softAssert.assertAll();
    }

    @Test(testName = "TC09: Search Product", dataProvider = "uiSearchQueries",
            dataProviderClass = TestDataProvider.class)
    public void searchProduct(String query, boolean expectEmpty) {
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
        ProductsPage productsPage = homePage.header.clickProductsLink();
        softAssert.assertTrue(productsPage.isAtProductsPage(), "Not at ALL Products page");
        productsPage.searchProduct(query);
        softAssert.assertTrue(productsPage.verifyProductsHeaderText("SEARCHED PRODUCTS"),
                "Searched Products header is not displayed, or text is incorrect");

        ProductsUiAssertions.assertProductsListEmptyState(expectEmpty, productsPage.isProductsListEmpty(), query);
        if (expectEmpty) {
            softAssert.assertAll();
            return;
        }
        List<String> uiProductNames = productsPage.getProductNames();

        ProductsApiClient apiClient = new ProductsApiClient();
        ResponseProducts apiResponse = apiClient.searchProduct(query);

        ProductsUiAssertions.assertUiMatchesApiProducts(uiProductNames, apiResponse.getProducts());
        softAssert.assertAll();
    }
}