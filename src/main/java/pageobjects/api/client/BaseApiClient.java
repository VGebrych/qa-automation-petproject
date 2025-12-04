package pageobjects.api.client;

import base.ApiSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected final RequestSpecification requestSpec;
    protected final ResponseSpecification responseSpec;

    protected BaseApiClient() {
        ApiSpecBuilder specBuilder = new ApiSpecBuilder();
        this.requestSpec = specBuilder.baseReq;
        this.responseSpec = specBuilder.baseResp;
    }

    // GET request
    protected Response get(String path) {
        return given()
                .spec(requestSpec)
                .when()
                .get(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // GET with query parameters
    protected Response get(String path, Map<String, ?> queryParams) {
        return given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // POST with form parameters
    protected Response postForm(String path, Map<String, String> formParams) {
        return given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .post(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // POST with JSON body
    protected Response postJson(String path, Object jsonBody) {
        return given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // PUT with JSON body
    protected Response put(String path, Object jsonBody) {
        return given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // PUT with form parameters
    protected Response putForm(String path, Map<String, String> formParams) {
        return given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .put(path)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }


    // DELETE with optional form parameters
    protected Response delete(String path, Map<String, String> formParams) {
        if (formParams != null && !formParams.isEmpty()) {
            return given()
                    .spec(requestSpec)
                    .formParams(formParams)
                    .when()
                    .delete(path)
                    .then()
                    .spec(responseSpec)
                    .extract()
                    .response();
        } else {
            return given()
                    .spec(requestSpec)
                    .when()
                    .delete(path)
                    .then()
                    .spec(responseSpec)
                    .extract()
                    .response();
        }
    }
}