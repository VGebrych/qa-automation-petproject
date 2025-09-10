package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.AccountInformation;
import pom.base.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
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
        nameInput.sendKeys(userName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillSignUpForm(String userName, String email) {
        enterName(userName);
        enterEmail(email);
    }

    public AccountInformation clickSignUpButton() {
        signUpButton.click();
        return new AccountInformation(driver);
    }
}
