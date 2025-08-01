package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    private static final Properties prop = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "/src/main/java/resources/GlobalData.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load GlobalData.properties", e);
        }
    }

    public static String getGlobalValue(String key) {
        return prop.getProperty(key);
    }


    public static String getValueFromJson(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.getString(key);
    }
}