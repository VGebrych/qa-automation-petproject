package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public abstract class BaseListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Clean allure-results folder before test suite starts
        String reportDir = "allure-results";
        File dir = new File(reportDir);
        if (dir.exists()) {
            try {
                FileUtils.cleanDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            dir.mkdirs();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // No specific action needed on finish
    }

    @Override
    public void onTestStart(ITestResult result) {
        saveTestName(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveStatus("TEST PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveStatus("TEST FAILED");
        saveThrowable(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        saveStatus("TEST SKIPPED");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        saveStatus("FAILED_TIMEOUT");
        saveThrowable(result.getThrowable());
    }

    // ======== Allure attachments ========
    @Attachment(value = "Test name", type = "text/plain")
    private String saveTestName(String testName) {
        return testName;
    }

    @Attachment(value = "Status", type = "text/plain")
    private String saveStatus(String status) {
        return status;
    }

    @Attachment(value = "Throwable", type = "text/plain")
    private String saveThrowable(Throwable throwable) {
        return throwable == null ? "No exception" : throwable.toString();
    }
}