package testUtils;

import io.restassured.path.json.JsonPath;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import pageobjects.api.account.User;
import pageobjects.api.account.UserRequest;

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

    public static void compareUserAccounts(UserRequest createdOrUpdatedUser, User actualGetUser, SoftAssert softAssert) {
        softAssert.assertEquals(actualGetUser.getName(), createdOrUpdatedUser.getName(), "Name mismatch");
        softAssert.assertEquals(actualGetUser.getEmail(), createdOrUpdatedUser.getEmail(), "Email mismatch");
        softAssert.assertEquals(actualGetUser.getTitle(), createdOrUpdatedUser.getTitle(), "Title mismatch");
        softAssert.assertEquals(actualGetUser.getBirth_day(), createdOrUpdatedUser.getBirth_date(), "Birth day mismatch");
        softAssert.assertEquals(actualGetUser.getBirth_month(), createdOrUpdatedUser.getBirth_month(), "Birth month mismatch");
        softAssert.assertEquals(actualGetUser.getBirth_year(), createdOrUpdatedUser.getBirth_year(), "Birth year mismatch");
        softAssert.assertEquals(actualGetUser.getFirst_name(), createdOrUpdatedUser.getFirstname(), "First name mismatch");
        softAssert.assertEquals(actualGetUser.getLast_name(), createdOrUpdatedUser.getLastname(), "Last name mismatch");
        softAssert.assertEquals(actualGetUser.getCompany(), createdOrUpdatedUser.getCompany(), "Company mismatch");
        softAssert.assertEquals(actualGetUser.getAddress1(), createdOrUpdatedUser.getAddress1(), "Address1 mismatch");
        softAssert.assertEquals(actualGetUser.getAddress2(), createdOrUpdatedUser.getAddress2(), "Address2 mismatch");
        softAssert.assertEquals(actualGetUser.getCountry(), createdOrUpdatedUser.getCountry(), "Country mismatch");
        softAssert.assertEquals(actualGetUser.getState(), createdOrUpdatedUser.getState(), "State mismatch");
        softAssert.assertEquals(actualGetUser.getCity(), createdOrUpdatedUser.getCity(), "City mismatch");
        softAssert.assertEquals(actualGetUser.getZipcode(), createdOrUpdatedUser.getZipcode(), "Zipcode mismatch");
    }
}