package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "title text-center")
    private WebElement allProductsHeader;


    public boolean isAtProductsPage() {
        return isAtPage("/products", allProductsHeader, "ALL PRODUCTS");
    }
}
