package pageobjects.ui.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.ui.pom.base.BasePage;

public class DeleteAccount extends BasePage {
    public DeleteAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title.text-center b")
    private WebElement accountDeletedTitle;

    @FindBy(css = ".pull-right [data-qa = 'continue-button']")
    private WebElement continueButton;

    public boolean isAccountDeletedTitleVisible(String expectedTitle) {
        return verifyElementText(accountDeletedTitle, expectedTitle);
    }

    public void clickContinueButton() {
        click(continueButton);
    }
}
