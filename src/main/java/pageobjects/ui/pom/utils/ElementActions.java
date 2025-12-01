package pageobjects.ui.pom.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        if (driver == null)
            throw new IllegalArgumentException("WebDriver must not be null");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // ----- Generic waits -----
    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForTextToAppear(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected void waitForTextToAppear(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // ----- Common actions -----
    protected void click(WebElement element) {
        waitForElementToAppear(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForElementToAppear(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean verifyElementText(WebElement element, String expectedText) {
        waitForElementToAppear(element);
        return element.isDisplayed() && element.getText().trim().equals(expectedText);
    }
}