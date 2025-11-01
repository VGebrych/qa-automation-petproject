package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserRequest;
import pom.CartPage;
import pom.ContactUsPage;
import testUtils.FileUtils;

@NeedUser
@NeedCleanUp
public class ContactAndSubscriptionTest extends BaseTestUI {
    //11 - Contact Us and Subscription tests will be implemented here

    @Test(testName = "TC06 - Contact Us Form Submission")
    public void testContactUsFormSubmission() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        ContactUsPage contactUsPage = homePage.header.clickContactUsLink();
        softAssert.assertTrue(contactUsPage.isGetInTouchHeaderVisible("GET IN TOUCH"),
                "'GET IN TOUCH' header is not visible");
        contactUsPage.fillBasicInfoToContactForm(user.getName(), user.getEmail(), "Test Subject");
        contactUsPage.enterMessage("This is a test message for the contact us form.");
        String filePath = FileUtils.getOrCreateUploadFile("contact_test_file.txt", "This is a test upload file.");
        contactUsPage.uploadFile(filePath);
        contactUsPage.clickSubmitButton();
        softAssert.assertTrue(contactUsPage.verifySuccessMessage("Success! Your details have been submitted successfully."),
                "Success message is not displayed as expected.");
        contactUsPage.clickHomeButton();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        softAssert.assertAll();
    }

    @Test(testName = "TC 10 - Verify Subscription in home page")
    public void testSubscriptionInHomePage() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        homePage.scrollToBottom();
        softAssert.assertTrue(homePage.footer.verifyNewsletterHeader("SUBSCRIPTION"),
                "'SUBSCRIPTION' header is not visible in footer");
        homePage.footer.subscribeWithEmail(user.getEmail());
        softAssert.assertTrue(homePage.footer.verifySubscriptionSuccessMessage(
                "You have been successfully subscribed!"),
                "Subscription success message is not displayed as expected.");
        softAssert.assertAll();
    }

    @Test (testName = "TC11 - Verify Subscription in Cart page")
    public void testSubscriptionInCartPage() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        CartPage cartpage = homePage.header.clickCartLink();
        cartpage.scrollToBottom();
        softAssert.assertTrue(cartpage.footer.verifyNewsletterHeader("SUBSCRIPTION"),
                "'SUBSCRIPTION' header is not visible in footer of Cart page");
        cartpage.footer.subscribeWithEmail(user.getEmail());
        softAssert.assertTrue(cartpage.footer.verifySubscriptionSuccessMessage(
                        "You have been successfully subscribed!"),
                "Subscription success message is not displayed as expected.");
        softAssert.assertAll();
    }
}