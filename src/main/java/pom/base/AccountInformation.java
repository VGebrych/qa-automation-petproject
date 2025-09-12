package pom.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountInformation extends BasePage {
    public AccountInformation(WebDriver driver) {
        super(driver);
    }

    // ---- Locators
    @FindBy (css = ".login-form >h2 > b")
    private WebElement accountInformationTitle;

    // ---- Actions
    public boolean isAccountInformationTitleVisible() {
        return isElementTextVisible(accountInformationTitle, "ENTER ACCOUNT INFORMATION");
    }

}