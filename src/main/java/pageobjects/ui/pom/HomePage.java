package pageobjects.ui.pom;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.ui.pom.base.BasePage;

import utils.Utils;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class='fc-button-label' and text()='Consent']")
    private WebElement consentButton;

    @FindBy(id = "slider")
    private WebElement homeSlider;

    @FindBy(id = "scrollUp")
    private WebElement scrollUpButton;

    @FindBy(css = ".item.active [class='col-sm-6'] h2")
    private WebElement sliderHeaderText;

    public void acceptCookies() {
        click(consentButton);
    }

    public void goToHomePage() {
        driver.get(Utils.getGlobalValue("baseURI"));
    }

    public void isAtHomePage() {
        boolean result = isAtPage(
                "https://automationexercise.com/",
                sliderHeaderText,
                "Full-Fledged practice website for Automation Engineers"
        );
        Assert.assertTrue(result, "Home page is not visible, or URL is incorrect, or text is not matching.");
    }

    public void scrollUpWithArrowButton() {
        click(scrollUpButton);
    }

    public boolean isSliderHeaderTextVisible(String expectedText) {
        return verifyElementText(sliderHeaderText, expectedText);
    }
}
