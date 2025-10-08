package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;
import pom.AccountCreatedPage;
import pom.SignupLoginPage;
import pom.SignupAccountInfoPage;

public class UserManagementTest extends BaseTestUI {
    @Test (testName = "TC 01 - Register User")
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

    @Test (testName = "TC 02 - Login User with correct email and password")
    @NeedUser
    @NeedCleanUp
    public void loginUserWithValidCredentials(){
        SoftAssert softAssert = new SoftAssert();
        UserRequest user = getPreconditionUser();

        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        SignupLoginPage loginPage = homePage.clickSignUpLoginLink();

        softAssert.assertTrue(loginPage.isLoginTextVisible("Login to your account"),
                "Login to your account text is not visible");

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();

        softAssert.assertTrue(homePage.isLoggedInAsVisible(user.getName()),
                "Logged in as username is not visible or wrong name is displayed");
        softAssert.assertAll();
    }

    @Test (testName = "TC 03 - Login User with incorrect email and password")
    public void loginUserWithInvalidCredentials(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");
        SignupLoginPage loginPage = homePage.clickSignUpLoginLink();
        softAssert.assertTrue(loginPage.isLoginTextVisible("Login to your account"),
                "Login to your account text is not visible");

        loginPage.fillLoginForm("somewrongemail@gmail.com", "somewrongpassword");
        loginPage.clickLoginButton();
        softAssert.assertTrue(loginPage.verifyWrongCredentialsAlertText("Your email or password is incorrect!"),
                "Alert text for wrong credentials is not visible or text is different than expected");
        softAssert.assertAll();
    }
}