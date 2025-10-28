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
    @FindBy(css = ".login-form h2")
    private WebElement getLoginText;

    @FindBy(css = ".signup-form h2")
    private WebElement getSignUpText;

    @FindBy(css = ".login-form input[name='email']")
    private  WebElement loginEmailInput;

    @FindBy(css = ".login-form input[name='password']")
    private WebElement loginPasswordInput;

    @FindBy(css = ".signup-form input[name='name']")
    private WebElement nameInput;

    @FindBy(css = ".signup-form input[name='email']")
    private WebElement emailInput;

    @FindBy(css = ".signup-form button.btn.btn-default")
    private WebElement signUpButton;

    @FindBy(css = ".login-form button.btn.btn-default")
    private WebElement loginButton;

    @FindBy (css = ".login-form p")
    private WebElement loginFormAlertText;

    @FindBy (css = ".signup-form p")
    private WebElement signUpFormAlertText;

    // ---- Actions
    public boolean isSignUpTextVisible(String expectedText) {
        waitForElementToAppear(getSignUpText);
        String actualText = getSignUpText.getText().trim();
        return actualText.equals(expectedText);
    }

    public boolean isLoginTextVisible(String expectedText) {
        waitForElementToAppear(getLoginText);
        String actualText = getLoginText.getText().trim();
        return actualText.equals(expectedText);
    }

    public void enterEmailLoginForm(String email) {
        waitForElementToAppear(loginEmailInput);
        loginEmailInput.sendKeys(email);
    }

    public void enterPasswordLoginForm(String password) {
        waitForElementToAppear(loginPasswordInput);
        loginPasswordInput.sendKeys(password);
    }

    public void enterNameSignUpForm(String userName) {
        waitForElementToAppear(nameInput);
        nameInput.sendKeys(userName);
    }

    public void enterEmailSignUpForm(String email) {
        waitForElementToAppear(emailInput);
        emailInput.sendKeys(email);
    }

    public void fillLoginForm(String email, String password) {
        enterEmailLoginForm(email);
        enterPasswordLoginForm(password);
    }

    public void fillSignUpForm(String userName, String email) {
        enterNameSignUpForm(userName);
        enterEmailSignUpForm(email);
    }

    public SignupAccountInfoPage clickSignUpButton() {
        click(signUpButton);
        return new SignupAccountInfoPage(driver);
    }

    public HomePage clickLoginButton() {
        click(loginButton);
        return new HomePage(driver);
    }

    public boolean verifyWrongCredentialsAlertText(String expectedText) {
        waitForElementToAppear(loginFormAlertText);
        String actualText = loginFormAlertText.getText().trim();
        return actualText.equals(expectedText);
    }

    public boolean verifyExistingEmailAlertText(String expectedText) {
        waitForElementToAppear(signUpFormAlertText);
        String actualText = signUpFormAlertText.getText().trim();
        return actualText.equals(expectedText);
    }
}
