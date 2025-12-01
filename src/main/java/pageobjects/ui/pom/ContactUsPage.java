package pageobjects.ui.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.ui.pom.base.BasePage;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".contact-form h2[class='title text-center']")
    private WebElement getInTouchHeader;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "subject")
    private WebElement subjectInput;

    @FindBy(name = "message")
    private WebElement messageTextArea;

    @FindBy(name = "upload_file")
    private WebElement uploadFileInput;

    @FindBy(name = "submit")
    private WebElement submitButton;

    @FindBy(css = ".status.alert.alert-success")
    private WebElement successMessage;

    @FindBy(css = ".btn-success")
    private WebElement homeButton;

    public boolean isGetInTouchHeaderVisible(String expectedText) {
        return verifyElementText(getInTouchHeader, expectedText);
    }

    public void enterName(String name) {
        type(nameInput, name);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterSubject(String subject) {
        type(subjectInput, subject);
    }

    public void enterMessage(String message) {
        type(messageTextArea, message);
    }

    public void uploadFile(String filePath) {
        waitForElementToAppear(uploadFileInput);
        uploadFileInput.sendKeys(filePath);
    }

    public void fillBasicInfoToContactForm(String name, String email, String subject) {
        enterName(name);
        enterEmail(email);
        enterSubject(subject);
    }

    public void clickSubmitButton() {
        click(submitButton);
        acceptAlert();
    }

    public boolean verifySuccessMessage(String expectedMessage) {
        return verifyElementText(successMessage, expectedMessage);
    }

    public HomePage clickHomeButton() {
        click(homeButton);
        return new HomePage(driver);
    }
}