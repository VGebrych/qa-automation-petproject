package pageobjects.ui.pom.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.ui.pom.Footer;
import pageobjects.ui.pom.Header;
import pageobjects.ui.pom.utils.ElementActions;

import java.time.Duration;

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

    public boolean isAtPage(String expectedUrlSubstring, WebElement webElement, String elementText) {
        return getCurrentUrl().contains(expectedUrlSubstring) &&
                verifyElementText(webElement, elementText);
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

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void hideGoogleAdsIfPresent() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait shortWait = (WebDriverWait) this.wait.withTimeout(Duration.ofSeconds(2));

        try {
            shortWait.until(d ->
                    (Boolean) ((JavascriptExecutor) d).executeScript(
                            "return document.querySelectorAll('iframe[id^=\"aswift_\"]').length > 0"
                    )
            );

            js.executeScript(
                    "document.querySelectorAll('iframe[id^=\"aswift_\"]').forEach(e => e.style.display = 'none');"
            );
        } catch (Exception ignored) {

        }

        try {
            Object root = shortWait.until(d ->
                    ((JavascriptExecutor) d).executeScript("return document.querySelector('div[role=\"region\"]');")
            );

            if (root == null) return;

            Long height = (Long) js.executeScript(
                    "let r = document.querySelector('div[role=\"region\"]');" +
                            "return r ? r.offsetHeight : 0;"
            );

            if (height != null && height > 150) { // Якщо банер розгорнутий
                WebElement collapseButton = (WebElement) js.executeScript(
                        "let r = document.querySelector('div[role=\"region\"]');" +
                                "if (!r || !r.shadowRoot) return null;" +
                                "return r.shadowRoot.querySelector('div[aria-label=\"Collapse\"], div[aria-label=\"Minimize\"]');"
                );

                if (collapseButton != null) {
                    collapseButton.click();
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void safeClick(WebElement element) {
        hideGoogleAdsIfPresent();
        scrollIntoView(element);
        click(element);
    }
}