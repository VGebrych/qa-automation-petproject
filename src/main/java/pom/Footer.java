package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pom.utils.ElementActions;

public class Footer extends ElementActions {
    public Footer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}