package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream("GlobalData.properties")) {
            if (input == null) {
                throw new RuntimeException("Failed to find GlobalData.properties in resources folder");
            }
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load GlobalData.properties", e);
        }
    }

    public static String getGlobalValue(String key) {
        return prop.getProperty(key);
    }
}