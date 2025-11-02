package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pom.utils.ElementActions;

public class Header extends ElementActions {

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Locators

    @FindBy(css = "#header a[href*='cart']")
    private WebElement CartLink;

    @FindBy(linkText = "Signup / Login")
    private WebElement signUpLoginLink;

    @FindBy(partialLinkText = "Test Cases")
    private WebElement TestCasesLink;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(partialLinkText = "Logout")
    private WebElement logOutButton;

    @FindBy(partialLinkText = "Delete Account")
    private WebElement deleteAccountButton;

    @FindBy(partialLinkText = "Contact us")
    private WebElement contactUsLink;


    //Actions

    public CartPage clickCartLink() {
        click(CartLink);
        return new CartPage(driver);
    }

    public SignupLoginPage clickSignUpLoginLink() {
        click(signUpLoginLink);
        return new SignupLoginPage(driver);
    }

    public TestCasesPage clickTestCasesLink() {
        click(TestCasesLink);
        return new TestCasesPage(driver);
    }

    public boolean isLoggedInAsVisible(String username) {
        String expectedText = "Logged in as " + username;
        return verifyElementText(loggedInAsText, expectedText);
    }

    public SignupLoginPage clickLogOutButton() {
        click(logOutButton);
        return new SignupLoginPage(driver);
    }

    public DeleteAccount clickDeleteAccountButton() {
        click(deleteAccountButton);
        return new DeleteAccount(driver);
    }

    public ContactUsPage clickContactUsLink() {
        click(contactUsLink);
        return new ContactUsPage(driver);
    }
}
