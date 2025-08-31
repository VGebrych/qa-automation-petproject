package tests.api;

import base.BaseTestApi;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.ApiTestUtils;

import static io.restassured.RestAssured.given;

@NeedUser
@NeedCleanUp
public class UserAuthenticationTest extends BaseTestApi {

    private final String verifyUserApiPath = "verifyLogin";

    @Test (testName = "API 7: POST To Verify Login with valid details")
    public void verifyUserLogin(){

        Response verifyUserResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", getPreconditionUser().getEmail())
                .formParam("password", getPreconditionUser().getPassword())
                .when().post(verifyUserApiPath)
                .then().extract().response();

        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "responseCode"), "200"
                , "Response code should be 200");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "message"),"User exists!");
    }

    @Test (testName = "API 8: POST To Verify Login without email parameter")
    public void verifyLoginWithoutEmail(){
        Response verifyUserResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", getPreconditionUser().getPassword())
                .when().post(verifyUserApiPath)
                .then().extract().response();
        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "responseCode"), "400"
                , "Response code should be 200");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "message")
                ,"Bad request, email or password parameter is missing in POST request.");
    }
}