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

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy (partialLinkText = "Logout")
    private WebElement logOutButton;

    @FindBy (partialLinkText = "Delete Account")
    private WebElement deleteAccountButton;


    // ---- Actions
    public void acceptCookies() {
        click(consentButton);
    }

    public SignupLoginPage clickSignUpLoginLink() {
        click(signUpLoginLink);
        return new SignupLoginPage(driver);
    }

    public void goToHomePage() {
        driver.get(Utils.getGlobalValue("baseURI"));
    }

    public boolean isHomeSliderVisible() {
        waitForElementToAppear(homeSlider);
        return homeSlider.isDisplayed();
    }

    public boolean isLoggedInAsVisible(String username) {
        waitForElementToAppear(loggedInAsText);
        String actualText = loggedInAsText.getText().trim();
        String expectedText = "Logged in as " + username;
        return actualText.equals(expectedText);
    }

    public void clickLogOutButton() {
        click(logOutButton);
    }

    public DeleteAccount clickDeleteAccountButton() {
        click(deleteAccountButton);
        return new DeleteAccount(driver);
    }
}
