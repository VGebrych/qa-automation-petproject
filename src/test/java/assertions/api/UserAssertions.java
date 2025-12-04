package assertions.api;

import org.testng.asserts.SoftAssert;
import pageobjects.api.account.User;
import pageobjects.api.account.UserRequest;

public class UserAssertions extends BaseApiAssertion{

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
