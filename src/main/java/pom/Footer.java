package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pom.utils.ElementActions;

public class Footer extends ElementActions {
    public Footer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".single-widget h2")
    private WebElement newsletterHeader;

    @FindBy (id = "susbscribe_email")
    private WebElement subscriptionEmailInput;

    @FindBy (id = "subscribe")
    private WebElement subscribeButton;

    @FindBy (id = "success-subscribe")
    private WebElement subscribeSuccessMessage;

    //Actions
    public boolean verifyNewsletterHeader(String expectedText){
        waitForElementToAppear(newsletterHeader);
        return newsletterHeader.getText().trim().equals(expectedText);
    }

    public void enterSubscriptionEmail(String email){
        type(subscriptionEmailInput, email);
    }

    public void clickSubscribeArrow(){
        click(subscribeButton);
    }

    public void subscribeWithEmail(String email){
        enterSubscriptionEmail(email);
        clickSubscribeArrow();
    }

    public boolean verifySubscriptionSuccessMessage(String expectedMessage){
        waitForElementToAppear(subscribeSuccessMessage);
        return subscribeSuccessMessage.getText().trim().equals(expectedMessage);
    }
}