package testUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTestUtils {

    public static String getValueFromJson(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.getString(key);
    }
}