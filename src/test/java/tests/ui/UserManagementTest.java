package tests.ui;

import base.BaseTestUI;
import base.annotations.NeedCleanUp;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.api.account.UserFactory;
import pom.LoginPage;

public class UserManagementTest extends BaseTestUI {
    @Test
    @NeedCleanUp
    public void registerUser() throws InterruptedException {
        methodLevelUser = UserFactory.createDefaultUser();

        homePage.acceptCookies();
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        LoginPage loginPage = homePage.clickSignUpLoginLink();
        Assert.assertTrue(loginPage.isSignUpTextVisible(), "Sign Up text is not visible");
        loginPage.fillSignUpForm(methodLevelUser.getName(), methodLevelUser.getEmail());
    }
}
