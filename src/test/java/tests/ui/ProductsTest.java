package tests.ui;

import base.BaseTestUI;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.ProductsPage;

public class ProductsTest extends BaseTestUI {
    //09, 18,19, 21,22 - Products tests will be implemented here
    @Test(testName = "TC08: Verify All Products and product detail page")
    public void verifyAllProductsAndProductDetailPage() {
        SoftAssert softAssert = new SoftAssert();
        homePage.assertAtHomePage(softAssert);
        ProductsPage productsPage = homePage.header.clickProductsLink();
        Assert.assertTrue(productsPage.isAtProductsPage(), "Not at Products page");


    }
}
