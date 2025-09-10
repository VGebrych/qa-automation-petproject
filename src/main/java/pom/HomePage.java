package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;
import utils.Utils;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ---- Locators
    @FindBy(xpath = "//p[@class='fc-button-label' and text()='Consent']")
    private WebElement consentButton;

    @FindBy(linkText = "Signup / Login")
    private WebElement signUpLoginLink;

    @FindBy(id = "slider")
    private WebElement homeSlider;


    // ---- Actions
    public void acceptCookies() {
        click(consentButton);
    }

    public LoginPage clickSignUpLoginLink() {
        click(signUpLoginLink);
        return new LoginPage(driver);
    }

    public HomePage goToHomePage() {
        driver.get(Utils.getGlobalValue("baseURI"));
        return new HomePage(driver);
    }

    public boolean isHomePageVisible() {
        waitForElementToAppear(homeSlider);
        return homeSlider.isDisplayed();
    }
}
