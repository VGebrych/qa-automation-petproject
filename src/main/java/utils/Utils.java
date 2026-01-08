package utils;

public class Utils {
    public static String normalizeString(String input) {
        if (input == null) return "";
        return input.toLowerCase().trim().replaceAll("[\\s&-]", "");
    }
}