package pageobjects.api.account;

import java.util.HashMap;
import java.util.Map;

public class UserFactory {

    private static String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp + "@test.test";
    }

    private static String generateUniqueMobileNumber() {
        long timestamp = System.currentTimeMillis() % 10000000;
        return "336" + timestamp;
    }

    private static String generateRandomTitle() {
        return Math.random() < 0.5 ? "Mr" : "Mrs";
    }

    public static UserRequest createDefaultUser() {
        UserRequest user = new UserRequest();
        user.setName("Test User");
        user.setEmail(generateUniqueEmail());
        user.setPassword("Password123");
        user.setTitle(generateRandomTitle());
        user.setBirth_date("15");
        user.setBirth_month("5");
        user.setBirth_year("1990");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setCompany("Test Company");
        user.setAddress1("123 Test St");
        user.setAddress2("Apt 4B");
        user.setCountry("United States");
        user.setZipcode("12345");
        user.setState("Test State");
        user.setCity("Test City");
        user.setMobile_number(generateUniqueMobileNumber());
        return user;
    }

    public static UserRequest createUpdatedUser(UserRequest existingUser) {
        UserRequest user = new UserRequest();
        user.setName("Updated Test User");
        user.setEmail(existingUser.getEmail());
        user.setPassword(existingUser.getPassword());
        user.setTitle(generateRandomTitle());
        user.setBirth_date("20");
        user.setBirth_month("10");
        user.setBirth_year("1985");
        user.setFirstname("UpdatedTest");
        user.setLastname("UpdatedUser");
        user.setCompany("Updated Test Company");
        user.setAddress1("456 Updated St");
        user.setAddress2("Apt 8C");
        user.setCountry("United States");
        user.setZipcode("54321");
        user.setState("Updated State");
        user.setCity("Updated City");
        user.setMobile_number(generateUniqueMobileNumber());
        return user;
    }

    public static Map<String, String> toMap(UserRequest user) {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", user.getName());
        formData.put("email", user.getEmail());
        formData.put("password", user.getPassword());
        formData.put("title", user.getTitle());
        formData.put("birth_date", user.getBirth_date());
        formData.put("birth_month", user.getBirth_month());
        formData.put("birth_year", user.getBirth_year());
        formData.put("firstname", user.getFirstname());
        formData.put("lastname", user.getLastname());
        formData.put("company", user.getCompany());
        formData.put("address1", user.getAddress1());
        formData.put("address2", user.getAddress2());
        formData.put("country", user.getCountry());
        formData.put("zipcode", user.getZipcode());
        formData.put("state", user.getState());
        formData.put("city", user.getCity());
        formData.put("mobile_number", user.getMobile_number());
        return formData;
    }
}