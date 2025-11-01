package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserRequest;
import pom.ContactUsPage;
import testUtils.FileUtils;

public class ContactAndSubscriptionTest extends BaseTestUI {
    //10, 11 - Contact Us and Subscription tests will be implemented here

    @Test(testName = "TC06 - Contact Us Form Submission")
    @NeedUser
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
}