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

    // =============================
    // GET
    // =============================
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

    // =============================
    // POST (form-url-encoded)
    // =============================
    protected Response postForm(String path, Map<String, ?> formParams) {
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

    // =============================
    // POST (JSON)
    // =============================
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

    // =============================
    // PUT (JSON)
    // =============================
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

    // =============================
    // PUT (form-url-encoded)
    // =============================
    protected Response putForm(String path, Map<String, ?> formParams) {
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

    // =============================
    // DELETE (optional form params)
    // =============================
    protected Response delete(String path, Map<String, ?> formParams) {
        if (formParams != null && !formParams.isEmpty()) {
            return given()
                    .spec(requestSpec)
                    .contentType("application/x-www-form-urlencoded")
                    .formParams(formParams)
                    .when()
                    .delete(path)
                    .then()
                    .spec(responseSpec)
                    .extract()
                    .response();
        }

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