package tests.api;

import base.BaseTestApi;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testUtils.ApiTestUtils;

import static io.restassured.RestAssured.given;

@NeedUser
@NeedCleanUp
@Test(groups = {"API"})
public class UserAuthenticationTest extends BaseTestApi {

    @DataProvider(name = "invalidLoginDetails")
    public Object[][] invalidLoginDetails() {
        return new Object[][]{
                {getPreconditionUser().getEmail(), "wrongPassword", "404", "User not found!"},
                {"somewrongemail@gmail.com", getPreconditionUser().getPassword(), "404", "User not found!"},
                {"somewrongemail@gmail.com", "wrongPassword", "404", "User not found!"},
                {"", "", "400", "Bad request, email or password parameter is missing in POST request."},
                {null, null, "400", "Bad request, email or password parameter is missing in POST request."},
                {getPreconditionUser().getEmail(), "", "400", "Bad request, email or password parameter is missing in POST request."},
                {"", getPreconditionUser().getPassword(), "400", "Bad request, email or password parameter is missing in POST request."}
        };
    }

    private final String verifyUserApiPath = "verifyLogin";

    @Test(testName = "API 7: POST To Verify Login with valid details", groups = {"API"})
    public void verifyUserLogin() {

        Response verifyUserResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", getPreconditionUser().getEmail())
                .formParam("password", getPreconditionUser().getPassword())
                .when().post(verifyUserApiPath)
                .then().extract().response();

        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "responseCode"), "200"
                , "Response code should be 200");
        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "message"), "User exists!");
    }

    @Test(testName = "API 9: DELETE To Verify Login", groups = {"API"})
    public void deleteToVerifyLogin() {
        ApiTestUtils.verifyMethodNotSupported(verifyUserApiPath, "DELETE", "405",
                "This request method is not supported.");
    }

    @Test(dataProvider = "invalidLoginDetails",
            testName = "API 8: POST To Verify Login without email parameter AND API 10: POST To Verify Login with invalid details",
            groups = {"API"})
    public void verifyLoginWithInvalidDetails(String email, String password, String code, String message) {
        RequestSpecification request = given().contentType("application/x-www-form-urlencoded");

        if (email != null && !email.isEmpty()) request.formParam("email", email);
        if (password != null && !password.isEmpty()) request.formParam("password", password);

        Response verifyUserResponse = request.post(verifyUserApiPath).then().extract().response();

        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "responseCode"), code);
        Assert.assertEquals(ApiTestUtils.getValueFromJson(verifyUserResponse, "message"), message);
    }
}