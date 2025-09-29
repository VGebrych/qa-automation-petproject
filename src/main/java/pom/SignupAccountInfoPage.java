package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class SignupAccountInfoPage extends BasePage {
    public SignupAccountInfoPage(WebDriver driver) {
        super(driver);
    }

    // ---- Locators
    @FindBy(css = ".login-form >h2 > b")
    private WebElement accountInformationTitle;

    @FindBy(id = "uniform-id_gender1")
    private WebElement mrTitleRadioButton;

    @FindBy(id = "uniform-id_gender2")
    private WebElement mrsTitleRadioButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daysDropdown;

    @FindBy(id = "months")
    private WebElement monthsDropdown;

    @FindBy(id = "years")
    private WebElement yearsDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

    // ---- Actions
    public boolean isAccountInformationTitleVisible() {
        waitForElementToAppear(accountInformationTitle);
        return isElementTextVisible(accountInformationTitle, "ENTER ACCOUNT INFORMATION");
    }

    public void setMrTitleRadioButton() {
        waitForElementToAppear(mrTitleRadioButton);
        click(mrTitleRadioButton);
    }

    public void setMrsTitleRadioButton() {
        waitForElementToAppear(mrsTitleRadioButton);
        click(mrsTitleRadioButton);
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            setMrTitleRadioButton();
        } else if (title.equalsIgnoreCase("Mrs")) {
            setMrsTitleRadioButton();
        }
    }

    public void enterName(String userName) {
        nameInput.sendKeys(userName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void selectDays(String day) {
        ;
    }

}