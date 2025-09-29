package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class SignupLoginPage extends BasePage {
    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    // ---- Locators
    @FindBy(css = ".signup-form h2")
    private WebElement getSignUpText;

    @FindBy(css = ".signup-form input[name='name']")
    private WebElement nameInput;

    @FindBy(css = ".signup-form input[name='email']")
    private WebElement emailInput;

    @FindBy(css = ".signup-form button.btn.btn-default")
    private WebElement signUpButton;

    // ---- Actions
    public boolean isSignUpTextVisible() {
        return isElementTextVisible(getSignUpText, "New User Signup!");
    }

    public void enterName(String userName) {
        waitForElementToAppear(nameInput);
        nameInput.sendKeys(userName);
    }

    public void enterEmail(String email) {
        waitForElementToAppear(emailInput);
        emailInput.sendKeys(email);
    }

    public void fillSignUpForm(String userName, String email) {
        enterName(userName);
        enterEmail(email);
    }

    public SignupAccountInfoPage clickSignUpButton() {
        waitForElementToAppear(signUpButton);
        signUpButton.click();
        return new SignupAccountInfoPage(driver);
    }
}
