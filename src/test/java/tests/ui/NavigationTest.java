package tests.ui;

import base.BaseTestUI;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.TestCasesPage;

public class NavigationTest extends BaseTestUI {
    @Test(testName = "TC07: Verify Test Cases Page")
    public void verifyTestCasesPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        TestCasesPage tcPage = homePage.header.clickTestCasesLink();
        softAssert.assertEquals(tcPage.getCurrentUrl(), "https://automationexercise.com/test_cases",
                "Test Cases page URL is incorrect");
        softAssert.assertTrue(tcPage.isTestCasesTitleVisible("TEST CASES"),
                "Test Cases title is not visible or wrong text is displayed");
        softAssert.assertAll();
    }

    @Test(testName = "TC25: 5: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpAndDownFunctionality() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        homePage.scrollToBottom();
        softAssert.assertTrue(homePage.isPageScrolledToBottom(), "Page did not scroll to Bottom");
        softAssert.assertTrue(homePage.footer.verifySubscriptionHeader("SUBSCRIPTION"),
                "Footer subscription header is not visible after scrolling down");
        homePage.scrollUpWithArrowButton();
        softAssert.assertTrue(homePage.isPageScrolledToTop(), "Page did not scroll to top after clicking 'Arrow' button");
        softAssert.assertTrue(homePage.isSliderHeaderTextVisible(
                        "Full-Fledged practice website for Automation Engineers"),
                "Slider header text is not visible or wrong text is displayed after scrolling up");
        softAssert.assertAll();
    }

    @Test(testName = "26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpWithoutArrowButton() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        homePage.scrollToBottom();
        softAssert.assertTrue(homePage.isPageScrolledToBottom(), "Page did not scroll to Bottom");
        softAssert.assertTrue(homePage.footer.verifySubscriptionHeader("SUBSCRIPTION"),
                "Footer subscription header is not visible after scrolling down");
        homePage.scrollToTop();
        softAssert.assertTrue(homePage.isPageScrolledToTop(), "Page did not scroll to top");
        softAssert.assertTrue(homePage.isSliderHeaderTextVisible(
                        "Full-Fledged practice website for Automation Engineers"),
                "Slider header text is not visible or wrong text is displayed after scrolling up");
        softAssert.assertAll();
    }
}