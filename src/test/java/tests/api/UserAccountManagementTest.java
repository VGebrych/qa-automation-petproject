package tests.api;

import base.BaseTestApi;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.account.*;
import testUtils.ApiTestUtils;

public class UserAccountManagementTest extends BaseTestApi {


    @Test(testName = "API 11: POST To Create/Register User Account")
    @NeedCleanUp
    public void createUserAccount() {
        UserRequest newUser = UserFactory.createDefaultUser();
        Response response = UserApiManager.createUser(newUser);

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "201");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "User created!");
    }

    @Test(testName = "API 12: DELETE METHOD To Delete User Account")
    @NeedUser
    public void deleteUserAccount() {
        Response response = UserApiManager.deleteUser(getActiveUser().getEmail(), getActiveUser().getPassword());

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "200");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "Account deleted!");
    }

    @Test(testName = "API 14: GET user account detail by email")
    @NeedUser
    @NeedCleanUp
    public void getUserAccountDetailsByEmail() {
        UserGetRequest userDetailsResponse = UserApiManager.getUserDetailByEmail(getActiveUser().getEmail());

        User getUser = userDetailsResponse.getUser();
        UserRequest activeUser = getActiveUser();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userDetailsResponse.getResponseCode(), 200);

        ApiTestUtils.compareUserAccounts(activeUser, getUser, softAssert);

        softAssert.assertAll();
    }
}