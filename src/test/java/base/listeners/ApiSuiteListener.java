package base.listeners;

import base.annotations.NeedUser;
import pageobjects.api.account.UserApiManager;
import pageobjects.api.account.UserFactory;
import pageobjects.api.account.UserRequest;
import org.testng.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApiSuiteListener implements ISuiteListener, IInvokedMethodListener {

    // Map to hold class-level users
    private final Map<Class<?>, UserRequest> classLevelUsers = new HashMap<>();

    @Override
    public void onStart(ISuite suite) {
        // Clean Allure results directory
        String resultsDir = "target/allure-results";
        File dir = new File(resultsDir);
        if (dir.exists()) {
            deleteDir(dir);
        }
    }

    private void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDir(f);
                } else {
                    if (!f.delete()) {
                        System.err.println("Failed to delete file: " + f.getAbsolutePath());
                    }
                }
            }
        }
        if (!dir.delete()) {
            System.err.println("Failed to delete directory: " + dir.getAbsolutePath());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        // Cleanup class-level users after suite finishes
        classLevelUsers.forEach((clazz, user) -> {
            if (user != null) {
                UserApiManager.deleteUser(user.getEmail(), user.getPassword());
            }
        });
        classLevelUsers.clear();
    }

    // IInvokedMethodListener methods
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        Method m = method.getTestMethod().getConstructorOrMethod().getMethod();
        Object instance = testResult.getInstance();

        // Handle method-level user
        if (m.isAnnotationPresent(NeedUser.class)) {
            UserRequest user = UserFactory.createDefaultUser();
            UserApiManager.createUser(user);
            if (instance instanceof base.BaseTest) {
                ((base.BaseTest) instance).methodLevelUser = user;
            }
        }

        // Handle class-level user
        Class<?> clazz = m.getDeclaringClass();
        if (clazz.isAnnotationPresent(NeedUser.class) && !classLevelUsers.containsKey(clazz)) {
            UserRequest user = UserFactory.createDefaultUser();
            UserApiManager.createUser(user);
            classLevelUsers.put(clazz, user);
            if (instance instanceof base.BaseTest) {
                ((base.BaseTest) instance).classLevelUser = user;
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        Method m = method.getTestMethod().getConstructorOrMethod().getMethod();
        Object instance = testResult.getInstance();

        // Delete method-level user after test
        if (m.isAnnotationPresent(NeedUser.class)) {
            if (instance instanceof base.BaseTest) {
                UserRequest user = ((base.BaseTest) instance).methodLevelUser;
                if (user != null) {
                    UserApiManager.deleteUser(user.getEmail(), user.getPassword());
                    ((base.BaseTest) instance).methodLevelUser = null;
                }
            }
        }
    }
}