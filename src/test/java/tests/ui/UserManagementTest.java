package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;
import pom.AccountCreatedPage;
import pom.DeleteAccount;
import pom.SignupLoginPage;
import pom.SignupAccountInfoPage;

public class UserManagementTest extends BaseTestUI {
    @Test(testName = "TC 01 - Register User")
    @NeedCleanUp
    public void registerUser() {
        methodLevelUser = UserFactory.createDefaultUser();
        SoftAssert softAssert = new SoftAssert();

        homePage.assertAtHomePage(softAssert);

        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();
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
        softAssert.assertTrue(homePage.header.isLoggedInAsVisible(methodLevelUser.getName()),
                "Logged in as username is not visible or wrong name is displayed");

        softAssert.assertAll();
    }

    @Test(testName = "TC 02 - Login User with correct email and password")
    @NeedUser
    @NeedCleanUp
    public void loginUserWithValidCredentials() {
        SoftAssert softAssert = new SoftAssert();
        UserRequest user = getPreconditionUser();

        homePage.assertAtHomePage(softAssert);
        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();

        softAssert.assertTrue(loginPage.isLoginTextVisible("Login to your account"),
                "Login to your account text is not visible");

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        homePage = loginPage.clickLoginButton();

        softAssert.assertTrue(homePage.header.isLoggedInAsVisible(user.getName()),
                "Logged in as username is not visible or wrong name is displayed");
        softAssert.assertAll();
    }

    @Test(testName = "TC 03 - Login User with incorrect email and password")
    public void loginUserWithInvalidCredentials() {
        SoftAssert softAssert = new SoftAssert();
        homePage.assertAtHomePage(softAssert);
        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();
        softAssert.assertTrue(loginPage.isLoginTextVisible("Login to your account"),
                "Login to your account text is not visible");

        loginPage.fillLoginForm("somewrongemail@gmail.com", "somewrongpassword");
        loginPage.clickLoginButton();
        softAssert.assertTrue(loginPage.verifyWrongCredentialsAlertText("Your email or password is incorrect!"),
                "Alert text for wrong credentials is not visible or text is different than expected");
        softAssert.assertAll();
    }

    @Test(testName = "Delete User Account")
    @NeedUser
    public void deleteUserAccount() {
        UserRequest user = getPreconditionUser();
        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        homePage = loginPage.clickLoginButton();
        DeleteAccount deleteAccount = homePage.header.clickDeleteAccountButton();
        Assert.assertTrue(deleteAccount.isAccountDeletedTitleVisible("ACCOUNT DELETED!"),
                "ACCOUNT DELETED title is not visible");
        deleteAccount.clickContinueButton();
    }

    @Test(testName = "TC 04 - Logout User")
    @NeedUser
    @NeedCleanUp
    public void logoutUser() {
        SoftAssert softAssert = new SoftAssert();
        UserRequest user = getPreconditionUser();

        homePage.assertAtHomePage(softAssert);
        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();

        softAssert.assertTrue(loginPage.isLoginTextVisible("Login to your account"),
                "Login to your account text is not visible");

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        homePage = loginPage.clickLoginButton();

        softAssert.assertTrue(homePage.header.isLoggedInAsVisible(user.getName()),
                "Logged in as username is not visible or wrong name is displayed");

        SignupLoginPage loginPageAfterLogOut = homePage.header.clickLogOutButton();
        softAssert.assertTrue(loginPageAfterLogOut.isLoginTextVisible("Login to your account"),
                "User is not redirected to login page after logout");
        softAssert.assertAll();
    }

    @Test(testName = "TC 05 - Register User with existing email")
    @NeedUser
    @NeedCleanUp
    public void registerUserWithExistingEmail() {
        SoftAssert softAssert = new SoftAssert();
        UserRequest user = getPreconditionUser();
        homePage.assertAtHomePage(softAssert);
        SignupLoginPage loginPage = homePage.header.clickSignUpLoginLink();
        softAssert.assertTrue(loginPage.isSignUpTextVisible("New User Signup!"),
                "Sign Up text is not visible");
        loginPage.fillSignUpForm("SomeName", user.getEmail());
        loginPage.clickSignUpButton();
        softAssert.assertTrue(loginPage.verifyExistingEmailAlertText("Email Address already exist!"),
                "Alert text for existing email is not visible or text is different than expected");
        softAssert.assertAll();
    }
}