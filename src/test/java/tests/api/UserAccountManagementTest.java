package tests.api;

import base.BaseTestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.api.account.UserApiManager;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;
import testUtils.ApiTestUtils;

public class UserAccountManagementTest extends BaseTestApi {

    @Test (testName = "API 11: POST To Create/Register User Account")
    public void createUserAccount() {
        UserRequest newUser = UserFactory.createDefaultUser();
        Response response = UserApiManager.createUser(newUser);

        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "responseCode"), "201");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(response, "message"), "User created!");
    }
}