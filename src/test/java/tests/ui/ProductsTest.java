package tests.ui;

import base.BaseTestUI;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.ProductDetailPage;
import pom.ProductsPage;

public class ProductsTest extends BaseTestUI {
    //09, 18,19, 21,22 - Products tests will be implemented here
    @Test(testName = "TC08: Verify All Products and product detail page")
    public void verifyAllProductsAndProductDetailPage() {
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
        ProductsPage productsPage = homePage.header.clickProductsLink();
        softAssert.assertTrue(productsPage.isAtProductsPage(), "Not at Products page");
        softAssert.assertTrue(productsPage.areProductsListVisible(), "Products are not visible");
        ProductDetailPage productDetailsPage = productsPage.clickOnFirstProductViewButton();
        softAssert.assertTrue(productDetailsPage.isAtProductDetailPage());
        productDetailsPage.verifyProductDetailsVisible(softAssert);
        softAssert.assertAll();
    }
}
