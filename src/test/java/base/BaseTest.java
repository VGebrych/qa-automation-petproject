package base;

import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobjects.api.client.UserApiClient;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;

import java.lang.reflect.Method;

public class BaseTest {

    public UserRequest methodLevelUser;
    public UserRequest classLevelUser;
    protected final UserApiClient userClient = new UserApiClient();

    @BeforeMethod
    public void setupMethodUser(Method method) {
        if (method.isAnnotationPresent(NeedUser.class)) {
            methodLevelUser = UserFactory.createDefaultUser();
            userClient.createUser(methodLevelUser);
        }
    }

    @AfterMethod
    public void cleanUpMethodUser(Method method) {
        if (methodLevelUser != null) {
            userClient.deleteUser(methodLevelUser.getEmail(), methodLevelUser.getPassword());
            methodLevelUser = null;
        }

        if (method.isAnnotationPresent(NeedCleanUp.class)) {
            UserRequest activeUser = getPreconditionUser();
            if (activeUser != null) {
                userClient.deleteUser(activeUser.getEmail(), activeUser.getPassword());
            }
        }
    }

    @BeforeClass
    public void setupClassClassUser() {
        RestAssured.requestSpecification = new ApiSpecBuilder().baseReq;
        if (this.getClass().isAnnotationPresent(NeedUser.class) && classLevelUser == null) {
            classLevelUser = UserFactory.createDefaultUser();
            userClient.createUser(classLevelUser);
        }
    }

    @AfterClass
    public void cleanUpClassUser() {
        if (classLevelUser != null) {
            userClient.deleteUser(classLevelUser.getEmail(), classLevelUser.getPassword());
            classLevelUser = null;
        }
    }

    protected UserRequest getPreconditionUser() {
        return methodLevelUser != null ? methodLevelUser : classLevelUser;
    }
}