package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.api.account.UserFactory;
import pom.SignupLoginPage;
import pom.SignupAccountInfoPage;

public class UserManagementTest extends BaseTestUI {
    @Test
    @NeedCleanUp
    public void registerUser() {
        methodLevelUser = UserFactory.createDefaultUser();

        Assert.assertTrue(homePage.isHomeSliderVisible(), "Home page is not visible");

        SignupLoginPage loginPage = homePage.clickSignUpLoginLink();
        Assert.assertTrue(loginPage.isSignUpTextVisible(), "Sign Up text is not visible");

        loginPage.fillSignUpForm(methodLevelUser.getName(), methodLevelUser.getEmail());
        SignupAccountInfoPage accountInfo = loginPage.clickSignUpButton();

        Assert.assertTrue(accountInfo.isAccountInformationTitleVisible(), "ACCOUNT INFORMATION title is not visible");

        accountInfo.fillAccountInformationForm(methodLevelUser);
        accountInfo.fillAddressInfoForm(methodLevelUser);
        accountInfo.clickCreateAccountButton();
    }
}