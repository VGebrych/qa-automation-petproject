package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.base.BasePage;

public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = ".title.title.text-center b")
    private WebElement testCasesTitle;


    public boolean isTestCasesTitleVisible(String titleText) {
        return verifyElementText(testCasesTitle, titleText);
    }
}
