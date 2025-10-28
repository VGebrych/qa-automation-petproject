package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    //Locators

    @FindBy(linkText = "Signup / Login")
    private WebElement signUpLoginLink;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy (partialLinkText = "Logout")
    private WebElement logOutButton;

    @FindBy (partialLinkText = "Delete Account")
    private WebElement deleteAccountButton;


    //Actions

    public SignupLoginPage clickSignUpLoginLink() {
        click(signUpLoginLink);
        return new SignupLoginPage(driver);
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
