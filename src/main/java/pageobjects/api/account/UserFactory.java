package pageobjects.api.account;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class UserFactory {

    private static final Faker faker = new Faker(Locale.FRANCE);

    private static String generateUniqueEmail() {
        return "user_" + UUID.randomUUID() + "@test.test";
    }

    private static String generateUniqueMobileNumber() {
        return "06" + faker.number().digits(8);
    }

    private static String generateRandomTitle() {
        return faker.options().option("Mr", "Mrs");
    }

    public static UserRequest createDefaultUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        UserRequest user = new UserRequest();
        user.setName(firstName + " " + lastName);
        user.setEmail(generateUniqueEmail());
        user.setPassword("Password123");
        user.setTitle(generateRandomTitle());
        user.setBirth_date("15");
        user.setBirth_month("5");
        user.setBirth_year("1990");
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setCompany(faker.company().name());
        user.setAddress1(faker.address().streetAddress());
        user.setAddress2(faker.address().secondaryAddress());
        user.setCountry("United States");
        user.setZipcode("12345");
        user.setState("Test State");
        user.setCity(faker.address().cityName());
        user.setMobile_number(generateUniqueMobileNumber());
        return user;
    }

    public static UserRequest createUpdatedUser(UserRequest existingUser) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        UserRequest user = new UserRequest();
        user.setName("Updated " + firstName + " " + lastName);
        user.setEmail(existingUser.getEmail());
        user.setPassword(existingUser.getPassword());
        user.setTitle(generateRandomTitle());
        user.setBirth_date("20");
        user.setBirth_month("10");
        user.setBirth_year("1985");
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setCompany("Updated " + faker.company().name());
        user.setAddress1("Updated " + faker.address().streetAddress());
        user.setAddress2("Updated " + faker.address().secondaryAddress());
        user.setCountry("United States");
        user.setZipcode("54321");
        user.setState("Updated State");
        user.setCity("Updated " + faker.address().cityName());
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