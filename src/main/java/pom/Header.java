package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    private void click(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
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

    @FindBy (partialLinkText = "Contact us")
    private WebElement contactUsLink;


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

    public SignupLoginPage clickLogOutButton() {
        click(logOutButton);
        return new SignupLoginPage(driver);
    }

    public DeleteAccount clickDeleteAccountButton() {
        click(deleteAccountButton);
        return new DeleteAccount(driver);
    }

    public ContactUsPage clickContactUsLink(){
        click(contactUsLink);
        return new ContactUsPage(driver);
    }
}
