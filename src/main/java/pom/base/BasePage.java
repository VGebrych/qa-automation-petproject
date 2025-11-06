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

    public boolean isPageScrolledToTop() {
        return wait.until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object scrollObj = js.executeScript("return window.scrollY || document.documentElement.scrollTop;");
            long scrollY = scrollObj != null ? ((Number) scrollObj).longValue() : 0L;
            return scrollY <= 2;
        });
    }

    public boolean isPageScrolledToBottom() {
        return wait.until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object scrollObj = js.executeScript(
                    "return window.scrollY || document.documentElement.scrollTop;"
            );
            Object innerHeightObj = js.executeScript(
                    "return window.innerHeight || document.documentElement.clientHeight;"
            );
            Object scrollHeightObj = js.executeScript(
                    "return document.documentElement.scrollHeight;"
            );

            long scrollY = scrollObj != null ? ((Number) scrollObj).longValue() : 0L;
            long innerHeight = innerHeightObj != null ? ((Number) innerHeightObj).longValue() : 0L;
            long scrollHeight = scrollHeightObj != null ? ((Number) scrollHeightObj).longValue() : 0L;

            return scrollY + innerHeight >= scrollHeight - 2;
        });
    }

}