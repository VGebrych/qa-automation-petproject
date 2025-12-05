package pageobjects.api.client;

import io.restassured.response.Response;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserGetRequest;
import pageobjects.api.account.UserRequest;

import java.util.HashMap;
import java.util.Map;

public class UserApiClient extends BaseApiClient {
    private static final String createAccount = "createAccount";
    private static final String getUserDetailByEmail = "getUserDetailByEmail";
    private static final String updateAccount = "updateAccount";
    private static final String deleteAccount = "deleteAccount";


    public Response createUser(UserRequest user) {
        Map<String, String> params = UserFactory.toMap(user);
        return postForm(createAccount, params);
    }

    public UserGetRequest getUserDetailByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return get(getUserDetailByEmail, params)
                .as(UserGetRequest.class);
    }

    public Response updateUser(UserRequest user) {
        Map<String, String> params = UserFactory.toMap(user);
        return putForm(updateAccount, params);
    }

    public Response deleteUser(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return delete(deleteAccount, params);
    }
}