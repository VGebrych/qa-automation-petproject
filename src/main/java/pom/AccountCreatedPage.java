package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class AccountCreatedPage extends BasePage {
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title.text-center b")
    private WebElement accountCreatedTitle;

    @FindBy(css = ".btn.btn-primary")
    private WebElement continueButton;

    // ---- Actions
    public boolean isAccountCreatedTitleVisible(String expectedText) {
        waitForElementToAppear(accountCreatedTitle);
        String actualText = accountCreatedTitle.getText().trim();
        return actualText.equalsIgnoreCase(expectedText);
    }

    public HomePage clickContinueButton() {
        click(continueButton);
        return new HomePage(driver);
    }
}