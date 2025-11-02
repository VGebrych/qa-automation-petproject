package tests.ui;

import base.BaseTestUI;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.TestCasesPage;

public class NavigationTest extends BaseTestUI {
    //25,26
    @Test (testName = "TC07: Verify Test Cases Page")
    public void verifyTestCasesPage(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        TestCasesPage tcPage = homePage.header.clickTestCasesLink();
        softAssert.assertEquals(tcPage.getCurrentUrl(), "https://automationexercise.com/test_cases", "Test Cases page URL is incorrect");
        softAssert.assertTrue(tcPage.isTestCasesTitleVisible("Test Cases"), "Test Cases title is not visible");
        softAssert.assertAll();
    }

    @Test (testName = "TC25: 5: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpAndDownFunctionality(){

    }
}
