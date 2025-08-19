package testUtils;

import io.restassured.path.json.JsonPath;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static io.restassured.RestAssured.given;

public class ApiTestUtils {
    public static <T> void verifyDuplicateIds(
            Iterable<T> items,
            Function<T, Integer> idExtractor,
            Function<T, String> nameExtractor,
            SoftAssert softAssert) {

        Set<Integer> ids = new HashSet<>();
        for (T item : items) {
            Integer id = idExtractor.apply(item);
            String name = nameExtractor.apply(item);
            softAssert.assertTrue(ids.add(id), "Duplicate ID found: " + name + " (ID: " + id + ")");
        }
    }

    public static void verifyMethodNotSupported(String apiPath, String method, String expectedCode, String expectedMessage) {
        Response response;

        switch (method.toUpperCase()) {
            case "POST":
                response = given().when().post(apiPath).then().extract().response();
                break;
            case "PUT":
                response = given().when().put(apiPath).then().extract().response();
                break;
            case "DELETE":
                response = given().when().delete(apiPath).then().extract().response();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getValueFromJson(response, "responseCode"), expectedCode);
        softAssert.assertEquals(getValueFromJson(response, "message"), expectedMessage);
        softAssert.assertAll();
    }

    public static String getValueFromJson(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.getString(key);
    }
}