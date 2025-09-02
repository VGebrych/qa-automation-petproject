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


    @Test(testName = "API 11: POST To Create/Register User Account", groups = {"API"})
    @NeedCleanUp
    public void createUserAccount() {
        UserRequest newUser = UserFactory.createDefaultUser();
        Response response = UserApiManager.createUser(newUser);

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "201");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "User created!");
    }

    @Test(testName = "API 12: DELETE METHOD To Delete User Account", groups = {"API"})
    @NeedUser
    public void deleteUserAccount() {
        Response response = UserApiManager.deleteUser(getPreconditionUser().getEmail(), getPreconditionUser().getPassword());

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "200");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "Account deleted!");
    }

    @Test(testName = "API 14: GET user account detail by email", groups = {"API"})
    @NeedUser
    @NeedCleanUp
    public void getUserAccountDetailsByEmail() {
        UserGetRequest userDetailsResponse = UserApiManager.getUserDetailByEmail(getPreconditionUser().getEmail());

        User getUser = userDetailsResponse.getUser();
        UserRequest preconditionUser = getPreconditionUser();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userDetailsResponse.getResponseCode(), 200);

        ApiTestUtils.compareUserAccounts(preconditionUser, getUser, softAssert);

        softAssert.assertAll();
    }

    @Test(testName = "API 13: PUT METHOD To Update User Account", groups = {"API"})
    @NeedUser
    @NeedCleanUp
    public void updateUserAccount(){
        UserRequest updatedUser = UserFactory.createUpdatedUser(getPreconditionUser());
        Response response = UserApiManager.updateUser(updatedUser);

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "200");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "User updated!");

        UserGetRequest userDetailsResponse = UserApiManager.getUserDetailByEmail(getPreconditionUser().getEmail());
        User currentUser = userDetailsResponse.getUser();

        ApiTestUtils.compareUserAccounts(updatedUser, currentUser, softAssert);

        softAssert.assertAll();
    }
}