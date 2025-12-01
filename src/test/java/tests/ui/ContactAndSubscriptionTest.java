package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserRequest;
import pageobjects.ui.pom.CartPage;
import pageobjects.ui.pom.ContactUsPage;
import pageobjects.ui.pom.HomePage;
import testUtils.FileUtils;

@NeedUser
@NeedCleanUp
public class ContactAndSubscriptionTest extends BaseTestUI {

    @Test(testName = "TC06 - Contact Us Form Submission")
    public void testContactUsFormSubmission() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
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
        HomePage homePage = contactUsPage.clickHomeButton();
        homePage.isAtHomePage();
        softAssert.assertAll();
    }

    @Test(testName = "TC 10 - Verify Subscription in home page")
    public void testSubscriptionInHomePage() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
        homePage.scrollToBottom();
        softAssert.assertTrue(homePage.footer.verifySubscriptionHeader("SUBSCRIPTION"),
                "'SUBSCRIPTION' header is not visible in footer");
        homePage.footer.subscribeWithEmail(user.getEmail());
        softAssert.assertTrue(homePage.footer.verifySubscriptionSuccessMessage(
                        "You have been successfully subscribed!"),
                "Subscription success message is not displayed as expected.");
        softAssert.assertAll();
    }

    @Test(testName = "TC11 - Verify Subscription in Cart page")
    public void testSubscriptionInCartPage() {
        UserRequest user = getPreconditionUser();
        SoftAssert softAssert = new SoftAssert();
        homePage.isAtHomePage();
        CartPage cartpage = homePage.header.clickCartLink();
        cartpage.scrollToBottom();
        softAssert.assertTrue(cartpage.footer.verifySubscriptionHeader("SUBSCRIPTION"),
                "'SUBSCRIPTION' header is not visible in footer of Cart page");
        cartpage.footer.subscribeWithEmail(user.getEmail());
        softAssert.assertTrue(cartpage.footer.verifySubscriptionSuccessMessage(
                        "You have been successfully subscribed!"),
                "Subscription success message is not displayed as expected.");
        softAssert.assertAll();
    }
}