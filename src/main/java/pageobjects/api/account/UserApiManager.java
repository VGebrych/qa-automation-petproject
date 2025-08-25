package pageobjects.api.account;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiManager {
    private final static String postCreateAccount = "createAccount";
    private final static String getUserDetailByEmail = "getUserDetailByEmail";
    private final static String updateAccount = "updateAccount";
    private final static String deleteAccount = "deleteAccount";


    public static Response createUser(UserRequest user){
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(UserFactory.toMap(user))
                .when()
                .post(postCreateAccount)
                .then().extract().response();
    }

    public static Response getUserDetailByEmail(String email){
        return given()
                .queryParam("email", email)
                .when()
                .get(getUserDetailByEmail)
                .then().extract().response();
    }

    public static Response updateUser(UserRequest user){
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(UserFactory.toMap(user))
                .when()
                .put(updateAccount)
                .then().extract().response();
    }

    public static Response deleteUser(String email, String password){
        return given()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(deleteAccount)
                .then().extract().response();
    }
}