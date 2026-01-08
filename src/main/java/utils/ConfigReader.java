package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_PATH = "src/test/resources/GlobalData.properties";

    static {
        Path path = Paths.get(CONFIG_PATH).toAbsolutePath();
        if (Files.notExists(path)) {
            throw new IllegalStateException("Configuration file not found: " + path);
        }

        try (InputStream input = Files.newInputStream(path)) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration from " + path, e);
        }
    }

    private ConfigReader() {
        // prevent instantiation
    }

    public static String getGlobalValue(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing key in GlobalData.properties: " + key);
        }
        return value.trim();
    }
}