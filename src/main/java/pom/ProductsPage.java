package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title.text-center")
    private WebElement ProductsHeader;

    @FindBy(className = "product-image-wrapper")
    List<WebElement> productCardsList;

    private By productNameInCard = By.tagName("p");

    @FindBy (id = "search_product")
    private WebElement searchInputField;

    @FindBy (id = "submit_search")
    private WebElement searchButton;




    public boolean isAtProductsPage() {
        return isAtPage("/products", ProductsHeader, "ALL PRODUCTS");
    }

    public boolean verifyProductsHeaderText(String expectedHeaderText){
        return verifyElementText(ProductsHeader, expectedHeaderText);
    }

    public boolean areProductsListVisible() {
        if (!productCardsList.isEmpty()) {
            for (WebElement productCard : productCardsList) {
                if (!productCard.isDisplayed()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void clickProductViewByIndex(int index) {

        if (productCardsList.isEmpty() || index < 0 || index >= productCardsList.size()) {
            throw new IllegalStateException("No product found at index: " + index);
        }

        WebElement product = productCardsList.get(index);
        WebElement viewButton = product.findElement(By.linkText("View Product"));
        safeClick(viewButton);
    }

    public ProductDetailPage clickOnFirstProductViewButton() {
        clickProductViewByIndex(0);
        return new ProductDetailPage(driver);
    }

    public void enterSearchProduct(String productName) {
        type(searchInputField, productName);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void searchProduct(String productName) {
        enterSearchProduct(productName);
        clickSearchButton();
    }
}