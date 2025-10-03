package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserFactory;
import pom.AccountCreatedPage;
import pom.SignupLoginPage;
import pom.SignupAccountInfoPage;

public class UserManagementTest extends BaseTestUI {
    @Test
    @NeedCleanUp
    public void registerUser() {
        methodLevelUser = UserFactory.createDefaultUser();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");

        SignupLoginPage loginPage = homePage.clickSignUpLoginLink();
        softAssert.assertTrue(loginPage.isSignUpTextVisible("New User Signup!"),
                "Sign Up text is not visible");

        loginPage.fillSignUpForm(methodLevelUser.getName(), methodLevelUser.getEmail());
        SignupAccountInfoPage accountInfo = loginPage.clickSignUpButton();

        softAssert.assertTrue(accountInfo.isAccountInformationTitleVisible("ENTER ACCOUNT INFORMATION"),
                "ACCOUNT INFORMATION title is not visible");

        accountInfo.fillAccountInformationForm(methodLevelUser);
        accountInfo.fillAddressInfoForm(methodLevelUser);
        AccountCreatedPage accountCreatedPage = accountInfo.clickCreateAccountButton();

        softAssert.assertTrue(accountCreatedPage.isAccountCreatedTitleVisible("ACCOUNT CREATED!"),
                "ACCOUNT CREATED! title is not visible");

        homePage = accountCreatedPage.clickContinueButton();
        softAssert.assertTrue(homePage.isLoggedInAsVisible(methodLevelUser.getName()),
                "Logged in as username is not visible or wrong name is displayed");

        softAssert.assertAll();
    }
}