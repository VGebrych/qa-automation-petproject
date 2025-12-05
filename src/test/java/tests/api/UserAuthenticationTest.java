package tests.api;

import assertions.api.UserAssertions;
import base.BaseTestApi;
import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.api.client.UserApiClient;

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

    @Test(testName = "API 7: POST To Verify Login with valid details", groups = {"API"})
    public void verifyUserLogin() {

        Response verifyUserResponse = userClient.verifyLogin(getPreconditionUser().getEmail(), getPreconditionUser().getPassword());
        UserAssertions.assertResponseCodeAndMessage(verifyUserResponse, "200", "User exists!");
    }

    @Test(testName = "API 9: DELETE To Verify Login", groups = {"API"})
    public void deleteToVerifyLogin() {
        UserAssertions.verifyMethodNotSupported(UserApiClient.getVerifyUserApiPath(), "DELETE", "405",
                "This request method is not supported.");
    }

    @Test(dataProvider = "invalidLoginDetails",
            testName = "API 8: POST To Verify Login without email parameter AND API 10: POST To Verify Login with invalid details",
            groups = {"API"})
    public void verifyLoginWithInvalidDetails(String email, String password, String code, String message) {
        Response verifyUserResponse = userClient.verifyLogin(email, password);
        UserAssertions.assertResponseCodeAndMessage(verifyUserResponse, code, message);
    }
}