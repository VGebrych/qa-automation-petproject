package pom.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pom.Footer;
import pom.Header;
import pom.utils.ElementActions;

public class BasePage extends ElementActions {
    public final Header header;
    public final Footer footer;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
        this.footer = new Footer(driver);
        PageFactory.initElements(driver, this);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

}