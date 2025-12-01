package pageobjects.ui.pom;

import org.testng.asserts.SoftAssert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.ui.pom.base.BasePage;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "product-information")
    private WebElement productInformationSection;

    @FindBy (css = ".product-information h2")
    private WebElement productName;

    @FindBy (xpath = "//p[starts-with(text(),'Category:')]")
    private WebElement productCategory;

    @FindBy (css = ".product-information span span")
    private WebElement productPrice;

    @FindBy (xpath = "//p[b[contains(text(),'Availability')]]")
    private WebElement productAvailability;

    @FindBy (xpath = "//p[b[contains(text(),'Condition')]]")
    private WebElement productCondition;

    @FindBy (xpath = "//p[b[contains(text(),'Brand')]]")
    private WebElement productBrand;

    public boolean isAtProductDetailPage() {
        try {
            return productInformationSection.isDisplayed() && getCurrentUrl().contains("product_details");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void verifyProductDetailsVisible(SoftAssert softAssert) {
        try {
            softAssert.assertTrue(productName.isDisplayed(), "Product name is not visible.");
            softAssert.assertTrue(productCategory.isDisplayed(), "Product category is not visible.");
            softAssert.assertTrue(productPrice.isDisplayed(), "Product price is not visible.");
            softAssert.assertTrue(productAvailability.isDisplayed(), "Product availability is not visible.");
            softAssert.assertTrue(productCondition.isDisplayed(), "Product condition is not visible.");
            softAssert.assertTrue(productBrand.isDisplayed(), "Product brand is not visible.");
        } catch (NoSuchElementException e) {
            softAssert.fail("One or more product detail elements are missing from DOM: " + e.getMessage());
        }
    }


}
