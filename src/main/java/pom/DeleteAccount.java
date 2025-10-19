package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class DeleteAccount extends BasePage {
    public DeleteAccount(WebDriver driver) {
        super(driver);
    }

    // ---- Locators
    @FindBy (css = ".title.text-center b")
    private WebElement accountDeletedTitle;

    @FindBy (css = ".pull-right [data-qa = 'continue-button']")
    private WebElement continueButton;

    // ---- Actions
    public boolean isAccountDeletedTitleVisible(String expectedTitle) {
        waitForElementToAppear(accountDeletedTitle);
        String actualTitle = accountDeletedTitle.getText().trim();
        return actualTitle.equals(expectedTitle);
    }

    public void clickContinueButton() {
        click(continueButton);
    }
}
