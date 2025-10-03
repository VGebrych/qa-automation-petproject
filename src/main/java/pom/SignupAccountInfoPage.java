package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageobjects.api.account.UserRequest;
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
        waitForElementToAppear(nameInput);
        nameInput.clear();
        nameInput.sendKeys(userName);
    }

    public void enterPassword(String password) {
        waitForElementToAppear(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void selectDay(String day) {
        waitForElementToAppear(daysDropdown);
        new Select(daysDropdown).selectByValue(day);
    }

    public void selectMonth(String month) {
        waitForElementToAppear(monthsDropdown);
        new Select(monthsDropdown).selectByValue(month);
    }

    public void selectYear(String year) {
        waitForElementToAppear(yearsDropdown);
        new Select(yearsDropdown).selectByValue(year);
    }

    public void checkNewsletterCheckbox() {
        waitForElementToAppear(newsletterCheckbox);
        if (!newsletterCheckbox.isSelected()) {
            click(newsletterCheckbox);
        }
    }

    public void checkSpecialOffersCheckbox() {
        waitForElementToAppear(specialOffersCheckbox);
        if (!specialOffersCheckbox.isSelected()) {
            click(specialOffersCheckbox);
        }
    }

    public void enterFirstName(String firstName) {
        waitForElementToAppear(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        waitForElementToAppear(lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterCompany(String company) {
        waitForElementToAppear(companyInput);
        companyInput.clear();
        companyInput.sendKeys(company);
    }

    public void enterAddress1(String address1) {
        waitForElementToAppear(address1Input);
        address1Input.clear();
        address1Input.sendKeys(address1);
    }

    public void enterAddress2(String address2) {
        waitForElementToAppear(address2Input);
        address2Input.clear();
        address2Input.sendKeys(address2);
    }

    public void selectCountry(String country) {
        waitForElementToAppear(countryDropdown);
        new Select(countryDropdown).selectByValue(country);
    }

    public void enterState(String state) {
        waitForElementToAppear(stateInput);
        stateInput.clear();
        stateInput.sendKeys(state);
    }

    public void enterCity(String city) {
        waitForElementToAppear(cityInput);
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        waitForElementToAppear(zipcodeInput);
        zipcodeInput.clear();
        zipcodeInput.sendKeys(zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        waitForElementToAppear(mobileNumberInput);
        mobileNumberInput.clear();
        mobileNumberInput.sendKeys(mobileNumber);
    }

    public void clickCreateAccountButton() {
        waitForElementToAppear(createAccountButton);
        click(createAccountButton);
    }

    public void fillAccountInformationForm(UserRequest user) {
        selectTitle(user.getTitle());
        enterName(user.getName());
        enterPassword(user.getPassword());
        selectDay(user.getBirth_date());
        selectMonth(user.getBirth_month());
        selectYear(user.getBirth_year());
        checkNewsletterCheckbox();
        checkSpecialOffersCheckbox();
    }

    public void fillAddressInfoForm(UserRequest user){
        enterFirstName(user.getFirstname());
        enterLastName(user.getLastname());
        enterCompany(user.getCompany());
        enterAddress1(user.getAddress1());
        enterAddress2(user.getAddress2());
        selectCountry(user.getCountry());
        enterState(user.getState());
        enterCity(user.getCity());
        enterZipcode(user.getZipcode());
        enterMobileNumber(user.getMobile_number());
    }
}