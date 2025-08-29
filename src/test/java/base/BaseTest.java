package base;

import base.annotations.NeedCleanUp;
import base.annotations.NeedUser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobjects.api.account.UserApiManager;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;

import java.lang.reflect.Method;

public class BaseTest {

    protected UserRequest methodLevelUser;
    protected UserRequest classLevelUser;

    @BeforeMethod
    public void setup(Method method) {
        if (method.isAnnotationPresent(NeedUser.class)) {
            methodLevelUser = UserFactory.createDefaultUser();
            UserApiManager.createUser(methodLevelUser);
        }
    }

    @AfterMethod
    public void cleanUp(Method method) {
        if (methodLevelUser != null) {
            UserApiManager.deleteUser(methodLevelUser.getEmail(), methodLevelUser.getPassword());
            methodLevelUser = null;
        }

        if (method.isAnnotationPresent(NeedCleanUp.class)) {
            UserRequest activeUser = getActiveUser();
            if (activeUser != null) {
                UserApiManager.deleteUser(activeUser.getEmail(), activeUser.getPassword());
            }
        }
    }

    @BeforeClass
    public void setupClass() {
        if (this.getClass().isAnnotationPresent(NeedUser.class) && classLevelUser == null) {
            classLevelUser = UserFactory.createDefaultUser();
            UserApiManager.createUser(classLevelUser);
        }
    }

    @AfterClass
    public void cleanUpClass() {
        if (classLevelUser != null) {
            UserApiManager.deleteUser(classLevelUser.getEmail(), classLevelUser.getPassword());
            classLevelUser = null;
        }
    }

    protected UserRequest getActiveUser() {
        return methodLevelUser != null ? methodLevelUser : classLevelUser;
    }
}