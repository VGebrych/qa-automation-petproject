package tests.ui;

import base.BaseTestUI;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.ContactUsPage;

public class ContactAndSubscriptionTest extends BaseTestUI {
    //10, 11 - Contact Us and Subscription tests will be implemented here

    @Test(testName = "TC06 - Contact Us Form Submission")
    public void testContactUsFormSubmission() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        ContactUsPage contactUsPage = homePage.header.clickContactUsLink();
        softAssert.assertTrue(contactUsPage.isGetInTouchHeaderVisible("GET IN TOUCH"),
                "'GET IN TOUCH' header is not visible");
    }
}
